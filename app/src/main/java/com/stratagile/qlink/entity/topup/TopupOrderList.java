package com.stratagile.qlink.entity.topup;

import android.os.Parcel;
import android.os.Parcelable;

import com.stratagile.qlink.entity.BaseBack;

import java.util.ArrayList;

public class TopupOrderList extends BaseBack<TopupOrderList.OrderListBean> {

    private ArrayList<OrderListBean> orderList;

    public ArrayList<OrderListBean> getOrderList() {
        return orderList;
    }

    public void setOrderList(ArrayList<OrderListBean> orderList) {
        this.orderList = orderList;
    }

    public static class OrderListBean implements Parcelable {

        /**
         * productIspEn : 移动
         * originalPrice : 50.0
         * discountPrice : 49.5
         * productCountryEn : 中国
         * productIsp : 移动
         * type : SLOW
         * userId : 5809297871f74fe48a4e080ec6ca7745
         * productName : 广东移动
         * number : 20190927103310117932
         * qgasAmount : 0.5
         * productProvinceEn : 广东
         * areaCode : +86
         * phoneNumber : 15989241234
         * orderTime : 2019-09-27 10:33:10
         * productNameEn : 广东移动
         * productCountry : 中国
         * id : d412f1f011944a54b992c02896c42c2e
         * productProvince : 广东
         * status : QGAS_PAID
         */

        private String productIspEn;
        private double originalPrice;
        private double discountPrice;
        private String productCountryEn;
        private String productIsp;
        private String type;
        private String userId;
        private String productName;
        private String number;
        private String txid;
        private double qgasAmount;
        private String productProvinceEn;
        private String areaCode;
        private String phoneNumber;
        private String orderTime;
        private String productNameEn;

        protected OrderListBean(Parcel in) {
            productIspEn = in.readString();
            originalPrice = in.readDouble();
            discountPrice = in.readDouble();
            productCountryEn = in.readString();
            productIsp = in.readString();
            type = in.readString();
            userId = in.readString();
            productName = in.readString();
            number = in.readString();
            txid = in.readString();
            qgasAmount = in.readDouble();
            productProvinceEn = in.readString();
            areaCode = in.readString();
            phoneNumber = in.readString();
            orderTime = in.readString();
            productNameEn = in.readString();
            productCountry = in.readString();
            id = in.readString();
            productProvince = in.readString();
            status = in.readString();
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

        public String getTxid() {
            return txid;
        }

        public void setTxid(String txid) {
            this.txid = txid;
        }

        private String productCountry;
        private String id;
        private String productProvince;
        private String status;

        public String getProductIspEn() {
            return productIspEn;
        }

        public void setProductIspEn(String productIspEn) {
            this.productIspEn = productIspEn;
        }

        public double getOriginalPrice() {
            return originalPrice;
        }

        public void setOriginalPrice(double originalPrice) {
            this.originalPrice = originalPrice;
        }

        public double getDiscountPrice() {
            return discountPrice;
        }

        public void setDiscountPrice(double discountPrice) {
            this.discountPrice = discountPrice;
        }

        public String getProductCountryEn() {
            return productCountryEn;
        }

        public void setProductCountryEn(String productCountryEn) {
            this.productCountryEn = productCountryEn;
        }

        public String getProductIsp() {
            return productIsp;
        }

        public void setProductIsp(String productIsp) {
            this.productIsp = productIsp;
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

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public double getQgasAmount() {
            return qgasAmount;
        }

        public void setQgasAmount(double qgasAmount) {
            this.qgasAmount = qgasAmount;
        }

        public String getProductProvinceEn() {
            return productProvinceEn;
        }

        public void setProductProvinceEn(String productProvinceEn) {
            this.productProvinceEn = productProvinceEn;
        }

        public String getAreaCode() {
            return areaCode;
        }

        public void setAreaCode(String areaCode) {
            this.areaCode = areaCode;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getOrderTime() {
            return orderTime;
        }

        public void setOrderTime(String orderTime) {
            this.orderTime = orderTime;
        }

        public String getProductNameEn() {
            return productNameEn;
        }

        public void setProductNameEn(String productNameEn) {
            this.productNameEn = productNameEn;
        }

        public String getProductCountry() {
            return productCountry;
        }

        public void setProductCountry(String productCountry) {
            this.productCountry = productCountry;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getProductProvince() {
            return productProvince;
        }

        public void setProductProvince(String productProvince) {
            this.productProvince = productProvince;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(productIspEn);
            dest.writeDouble(originalPrice);
            dest.writeDouble(discountPrice);
            dest.writeString(productCountryEn);
            dest.writeString(productIsp);
            dest.writeString(type);
            dest.writeString(userId);
            dest.writeString(productName);
            dest.writeString(number);
            dest.writeString(txid);
            dest.writeDouble(qgasAmount);
            dest.writeString(productProvinceEn);
            dest.writeString(areaCode);
            dest.writeString(phoneNumber);
            dest.writeString(orderTime);
            dest.writeString(productNameEn);
            dest.writeString(productCountry);
            dest.writeString(id);
            dest.writeString(productProvince);
            dest.writeString(status);
        }
    }
}