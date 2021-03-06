package com.stratagile.qlink.ui.adapter.otc;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.stratagile.qlink.R;
import com.stratagile.qlink.application.AppConfig;
import com.stratagile.qlink.constant.ConstantValue;
import com.stratagile.qlink.data.api.API;
import com.stratagile.qlink.data.api.MainAPI;
import com.stratagile.qlink.entity.EntrustOrderList;
import com.stratagile.qlink.utils.AccountUtil;

import java.math.BigDecimal;
import java.util.List;

public class EntrustOrderListAdapter extends BaseQuickAdapter<EntrustOrderList.OrderListBean, BaseViewHolder> {

    public EntrustOrderListAdapter(@Nullable List<EntrustOrderList.OrderListBean> data) {
        super(R.layout.entrust_order_list_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, EntrustOrderList.OrderListBean item) {
        helper.setText(R.id.tvUnitPrice, BigDecimal.valueOf(item.getUnitPrice()).stripTrailingZeros().toPlainString());
        helper.setText(R.id.tvPayToken, item.getPayToken());
        helper.setText(R.id.tvTradeToken0, " " + item.getTradeToken() + "");
        helper.setText(R.id.tvTradeToken1, " " + item.getTradeToken() + "");
        if (item.getType().equals(ConstantValue.orderTypeSell)) {
            helper.setText(R.id.tvOpreate, mContext.getResources().getString(R.string.buy).toUpperCase());
            helper.setBackgroundRes(R.id.tvOpreate, R.drawable.buy_bg);
        } else {
            helper.setText(R.id.tvOpreate, mContext.getResources().getString(R.string.sell).toUpperCase());
            helper.setBackgroundRes(R.id.tvOpreate, R.drawable.sell_bg);
        }
        if ("1".equals(item.getIsBurnQgasOrder())) {
            helper.setVisible(R.id.tvTeam, true);
        } else {
            helper.setVisible(R.id.tvTeam, false);
        }
        helper.setText(R.id.tvDeals, item.getOtcTimes() + " Deals");
        helper.setText(R.id.tvNickName, AccountUtil.setUserNickName(item.getNickname()));
        helper.setText(R.id.tvAmount, (BigDecimal.valueOf(item.getTotalAmount()).subtract(BigDecimal.valueOf(item.getLockingAmount())).subtract(BigDecimal.valueOf(item.getCompleteAmount())).stripTrailingZeros().toPlainString()));
        if (BigDecimal.valueOf(item.getTotalAmount()).intValue() - BigDecimal.valueOf(item.getLockingAmount()).intValue() - BigDecimal.valueOf(item.getCompleteAmount()).intValue() < BigDecimal.valueOf(item.getMaxAmount()).intValue()) {
            helper.setText(R.id.tvQgasVolume, BigDecimal.valueOf(item.getMinAmount()).stripTrailingZeros().toPlainString() + " - " + (BigDecimal.valueOf(item.getTotalAmount()).subtract(BigDecimal.valueOf(item.getLockingAmount())).subtract(BigDecimal.valueOf(item.getCompleteAmount())).stripTrailingZeros().toPlainString()));
        } else {
            helper.setText(R.id.tvQgasVolume, BigDecimal.valueOf(item.getMinAmount()).stripTrailingZeros().toPlainString() + " - " + BigDecimal.valueOf(item.getMaxAmount()).stripTrailingZeros().toPlainString());
        }
        Glide.with(mContext)
                .load(AppConfig.getInstance().getBaseUrl() + item.getHead())
                .apply(AppConfig.getInstance().options)
                .into((ImageView) helper.getView(R.id.ivAvatar));
        Glide.with(mContext)
                .load(AppConfig.getInstance().getBaseUrl() + item.getTradeTokenLogo())
                .apply(AppConfig.getInstance().optionsNormal)
                .into((ImageView) helper.getView(R.id.ivTradeToken));
        Glide.with(mContext)
                .load(AppConfig.getInstance().getBaseUrl() + item.getPayTokenLogo())
                .apply(AppConfig.getInstance().optionsNormal)
                .into((ImageView) helper.getView(R.id.ivPayToken));
    }
}
