package com.stratagile.qlink.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.format.DateUtils;
import android.util.Log;


import com.socks.library.KLog;
import com.stratagile.qlink.entity.walletconnect.WCRequest;
import com.stratagile.qlink.walletconnect.WCClient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import kotlin.Unit;

/**
 * The purpose of this service is to manage the currently active WalletConnect sessions. Keep the connections alive and terminate where required.
 * Doing this in an activity means the connection objects are subject to activity lifecycle events (Destroy etc).
 */
public class WalletConnectService extends Service
{
    private final ConcurrentHashMap<String, WCClient> clientMap = new ConcurrentHashMap<>();
    private final ConcurrentLinkedQueue<WCRequest> signRequests = new ConcurrentLinkedQueue<>();

    private final ConcurrentHashMap<String, Long> clientTimes = new ConcurrentHashMap<>();

    private static final String TAG = "WCClientSvs";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        KLog.i("SERVICE STARTING");
        return START_STICKY;
    }

    private final IBinder mBinder = new LocalBinder();

    public WCRequest getPendingRequest(String sessionId)
    {
        WCRequest request = signRequests.poll();
        if (request != null && !request.sessionId.equals(sessionId))
        {
            signRequests.add(request); // not for this client, put it back on the stack, at the back
            request = null;
        }

        return request;
    }

    public int getConnectionCount()
    {
        return clientMap.size();
    }

    public class LocalBinder extends Binder
    {
        public WalletConnectService getService()
        {
            return WalletConnectService.this;
        }
    }

    @Nullable
    private Disposable pingTimer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        return mBinder;
    }

    public WCClient getClient(String sessionId)
    {
        return clientMap.get(sessionId);
    }

    public void putClient(String sessionId, WCClient client)
    {
        KLog.i("Add session: " + sessionId);
        clientMap.put(sessionId, client);
        clientTimes.put(sessionId, System.currentTimeMillis());
        setupClient(client);
        startSessionPinger();
    }

    public void rejectRequest(String sessionId, long id, String message)
    {
        WCClient c = clientMap.get(sessionId);
        if (c != null && c.isConnected())
        {
            c.rejectRequest(id, message);
        }
    }

    public void approveRequest(String sessionId, long id, String message)
    {
        WCClient c = clientMap.get(sessionId);
        if (c != null && c.isConnected())
        {
            c.approveRequest(id, message);
        }
    }

    private void setupClient(WCClient client)
    {
        client.setOnSessionRequest((id, peer) -> {
            KLog.i("setOnSessionRequest");
            if (client.sessionId() == null) return Unit.INSTANCE;
            setLastUsed(client);
            signRequests.add(new WCRequest(client.sessionId(), id, peer));
            KLog.i("On Request: " + peer.getName());
            return Unit.INSTANCE;
        });

        client.setOnFailure(throwable -> {
            KLog.i("setOnFailure");
            if (client.sessionId() == null) return Unit.INSTANCE;
            signRequests.add(new WCRequest(client.sessionId(), throwable));
            return Unit.INSTANCE;
        });

        client.setOnEthSign((id, message) -> {
            KLog.i("setOnEthSign");
            if (client.sessionId() == null) return Unit.INSTANCE;
            setLastUsed(client);
            signRequests.add(new WCRequest(client.sessionId(), id, message));
            return Unit.INSTANCE;
        });

        client.setOnEthSignTransaction((id, transaction) -> {
            KLog.i("setOnEthSignTransaction");
            if (client.sessionId() == null) return Unit.INSTANCE;
            setLastUsed(client);
            signRequests.add(new WCRequest(client.sessionId(), id, transaction, true));
            return Unit.INSTANCE;
        });

        client.setOnEthSendTransaction((id, transaction) -> {
            KLog.i("setOnEthSendTransaction");
            if (client.sessionId() == null) return Unit.INSTANCE;
            setLastUsed(client);
            signRequests.add(new WCRequest(client.sessionId(), id, transaction, false));
            return Unit.INSTANCE;
        });
    }

    private void startSessionPinger()
    {
        if (pingTimer == null || pingTimer.isDisposed())
        {
            pingTimer = Observable.interval(0, 30, TimeUnit.SECONDS)
                    .doOnNext(l -> ping()).subscribe();
        }
    }

    private void ping()
    {
        List<String> removeKeyList = new ArrayList<>();
        for (String sessionKey : clientMap.keySet())
        {
            WCClient c = clientMap.get(sessionKey);
            if (c.isConnected() && c.getChainId() != null && c.getAccounts() != null)
            {
                KLog.i("Ping Key: " + sessionKey);
                c.approveSession(c.getAccounts(), c.getChainId());
            }

            long lastUsed = getLastUsed(c);
            long timeUntilTerminate = DateUtils.MINUTE_IN_MILLIS*5 - (System.currentTimeMillis() - lastUsed);
            KLog.i("Time until terminate: " + timeUntilTerminate/DateUtils.SECOND_IN_MILLIS + " (" + sessionKey + ")");
            if ((System.currentTimeMillis() - lastUsed) > DateUtils.MINUTE_IN_MILLIS*5)
            {
                if (c.getSession() != null)
                {
                    KLog.i("Terminate session: " + sessionKey);
                    c.killSession();
                }
                else
                {
                    KLog.i( "Disconnect session: " + sessionKey);
                    c.disconnect();
                }
                removeKeyList.add(sessionKey);
            }
        }

        for (String removeKey : removeKeyList)
        {
            KLog.i("Removing Key: " + removeKey);
            clientMap.remove(removeKey);
            if (clientMap.size() == 0 && pingTimer != null && !pingTimer.isDisposed())
            {
                KLog.i("Stop timer & service");
                pingTimer.dispose();
                pingTimer = null;
                stopSelf();
            }
        }
    }

    @Override
    public void onDestroy() {
        KLog.i("服务关闭");
        super.onDestroy();
    }

    private long getLastUsed(WCClient c)
    {
        String sessionId = c.sessionId();
        if (sessionId != null && clientTimes.containsKey(sessionId)) return clientTimes.get(sessionId);
        else return 0;
    }

    private void setLastUsed(WCClient c)
    {
        String sessionId = c.sessionId();
        if (sessionId != null) clientTimes.put(sessionId, System.currentTimeMillis());
    }


}
