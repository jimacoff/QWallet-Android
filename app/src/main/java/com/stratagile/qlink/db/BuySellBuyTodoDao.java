package com.stratagile.qlink.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "BUY_SELL_BUY_TODO".
*/
public class BuySellBuyTodoDao extends AbstractDao<BuySellBuyTodo, Long> {

    public static final String TABLENAME = "BUY_SELL_BUY_TODO";

    /**
     * Properties of entity BuySellBuyTodo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Account = new Property(1, String.class, "account", false, "ACCOUNT");
        public final static Property Token = new Property(2, String.class, "token", false, "TOKEN");
        public final static Property TradeOrderId = new Property(3, String.class, "tradeOrderId", false, "TRADE_ORDER_ID");
        public final static Property Txid = new Property(4, String.class, "txid", false, "TXID");
    }


    public BuySellBuyTodoDao(DaoConfig config) {
        super(config);
    }
    
    public BuySellBuyTodoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"BUY_SELL_BUY_TODO\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"ACCOUNT\" TEXT," + // 1: account
                "\"TOKEN\" TEXT," + // 2: token
                "\"TRADE_ORDER_ID\" TEXT," + // 3: tradeOrderId
                "\"TXID\" TEXT);"); // 4: txid
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"BUY_SELL_BUY_TODO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, BuySellBuyTodo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String account = entity.getAccount();
        if (account != null) {
            stmt.bindString(2, account);
        }
 
        String token = entity.getToken();
        if (token != null) {
            stmt.bindString(3, token);
        }
 
        String tradeOrderId = entity.getTradeOrderId();
        if (tradeOrderId != null) {
            stmt.bindString(4, tradeOrderId);
        }
 
        String txid = entity.getTxid();
        if (txid != null) {
            stmt.bindString(5, txid);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, BuySellBuyTodo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String account = entity.getAccount();
        if (account != null) {
            stmt.bindString(2, account);
        }
 
        String token = entity.getToken();
        if (token != null) {
            stmt.bindString(3, token);
        }
 
        String tradeOrderId = entity.getTradeOrderId();
        if (tradeOrderId != null) {
            stmt.bindString(4, tradeOrderId);
        }
 
        String txid = entity.getTxid();
        if (txid != null) {
            stmt.bindString(5, txid);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public BuySellBuyTodo readEntity(Cursor cursor, int offset) {
        BuySellBuyTodo entity = new BuySellBuyTodo( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // account
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // token
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // tradeOrderId
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4) // txid
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, BuySellBuyTodo entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setAccount(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setToken(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setTradeOrderId(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setTxid(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(BuySellBuyTodo entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(BuySellBuyTodo entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(BuySellBuyTodo entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
