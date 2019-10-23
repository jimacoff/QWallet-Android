package com.stratagile.qlink.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class EntrustOrderList extends BaseBack<EntrustOrderList.OrderListBean> {


    private ArrayList<OrderListBean> orderList;

    public ArrayList<OrderListBean> getOrderList() {
        return orderList;
    }

    public void setOrderList(ArrayList<OrderListBean> orderList) {
        this.orderList = orderList;
    }

    public static class OrderListBean implements Parcelable {

        /**
         * unitPrice : 1.0
         * minAmount : 1.0
         * lockingAmount : 1.0
         * type : SELL
         * userId : 61be9c09c0784827af303005f983c705
         * tradeTokenChain : NEO_CHAIN
         * completeAmount : 0.0
         * head :
         * number : 20190823212742197841
         * totalAmount : 2.0
         * orderTime : 2019-08-23 21:27:42
         * nickname : ios_test
         * payTokenChain : QLC_CHAIN
         * tradeToken : QLC
         * id : 38013bdf37f84c4fad8e688dd186bb39
         * maxAmount : 2.0
         * otcTimes : 0
         * status : NORMAL
         * payToken : QGAS
         */

        private double unitPrice;
        private double minAmount;
        private double lockingAmount;
        private String type;
        private String userId;
        private String tradeTokenChain;
        private double completeAmount;
        private String head;
        private String number;
        private double totalAmount;
        private String orderTime;
        private String nickname;
        private String payTokenChain;
        private String tradeToken;
        private String id;
        private double maxAmount;
        private int otcTimes;
        private String status;
        private String payToken;

        protected OrderListBean(Parcel in) {
            unitPrice = in.readDouble();
            minAmount = in.readDouble();
            lockingAmount = in.readDouble();
            type = in.readString();
            userId = in.readString();
            tradeTokenChain = in.readString();
            completeAmount = in.readDouble();
            head = in.readString();
            number = in.readString();
            totalAmount = in.readDouble();
            orderTime = in.readString();
            nickname = in.readString();
            payTokenChain = in.readString();
            tradeToken = in.readString();
            id = in.readString();
            maxAmount = in.readDouble();
            otcTimes = in.readInt();
            status = in.readString();
            payToken = in.readString();
        }

        public static final Creator<OrderListBean> CREATOR = new Creator<OrderListBean>() {
            @Override
            public OrderListBean createFromParcel(Parcel in) {
                return new OrderListBean(in);
            }

            @Override
            public OrderListBean[] newArray(int size) {
                return new OrderListBean[size];
            }
        };

        public double getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(double unitPrice) {
            this.unitPrice = unitPrice;
        }

        public double getMinAmount() {
            return minAmount;
        }

        public void setMinAmount(double minAmount) {
            this.minAmount = minAmount;
        }

        public double getLockingAmount() {
            return lockingAmount;
        }

        public void setLockingAmount(double lockingAmount) {
            this.lockingAmount = lockingAmount;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getTradeTokenChain() {
            return tradeTokenChain;
        }

        public void setTradeTokenChain(String tradeTokenChain) {
            this.tradeTokenChain = tradeTokenChain;
        }

        public double getCompleteAmount() {
            return completeAmount;
        }

        public void setCompleteAmount(double completeAmount) {
            this.completeAmount = completeAmount;
        }

        public String getHead() {
            return head;
        }

        public void setHead(String head) {
            this.head = head;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public double getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(double totalAmount) {
            this.totalAmount = totalAmount;
        }

        public String getOrderTime() {
            return orderTime;
        }

        public void setOrderTime(String orderTime) {
            this.orderTime = orderTime;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPayTokenChain() {
            return payTokenChain;
        }

        public void setPayTokenChain(String payTokenChain) {
            this.payTokenChain = payTokenChain;
        }

        public String getTradeToken() {
            return tradeToken;
        }

        public void setTradeToken(String tradeToken) {
            this.tradeToken = tradeToken;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public double getMaxAmount() {
            return maxAmount;
        }

        public void setMaxAmount(double maxAmount) {
            this.maxAmount = maxAmount;
        }

        public int getOtcTimes() {
            return otcTimes;
        }

        public void setOtcTimes(int otcTimes) {
            this.otcTimes = otcTimes;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getPayToken() {
            return payToken;
        }

        public void setPayToken(String payToken) {
            this.payToken = payToken;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeDouble(unitPrice);
            parcel.writeDouble(minAmount);
            parcel.writeDouble(lockingAmount);
            parcel.writeString(type);
            parcel.writeString(userId);
            parcel.writeString(tradeTokenChain);
            parcel.writeDouble(completeAmount);
            parcel.writeString(head);
            parcel.writeString(number);
            parcel.writeDouble(totalAmount);
            parcel.writeString(orderTime);
            parcel.writeString(nickname);
            parcel.writeString(payTokenChain);
            parcel.writeString(tradeToken);
            parcel.writeString(id);
            parcel.writeDouble(maxAmount);
            parcel.writeInt(otcTimes);
            parcel.writeString(status);
            parcel.writeString(payToken);
        }
    }
}