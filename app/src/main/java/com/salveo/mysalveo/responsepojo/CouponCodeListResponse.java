package com.salveo.mysalveo.responsepojo;

import java.util.List;

public class CouponCodeListResponse {


    /**
     * Status : Success
     * Message : Coupon Code List
     * Data : [{"coupon_type":"NORMAL CODE","coupon_code":"TRITON123","expired_date":"2021-10-08T00:00:00.000Z","title":"","descri":"","used_status":""},{"coupon_type":"NORMAL CODE","coupon_code":"SRI50","expired_date":"2021-10-08T00:00:00.000Z","title":"","descri":"","used_status":""},{"coupon_type":"NORMAL CODE","coupon_code":"DIN100","expired_date":"2021-10-08T00:00:00.000Z","title":"","descri":"","used_status":""},{"coupon_type":"NORMAL CODE","coupon_code":"DIN100","expired_date":"2021-08-30T00:00:00.000Z","title":"","descri":"","used_status":""},{"coupon_type":"NORMAL CODE","coupon_code":"DINE100","expired_date":"2021-08-30T00:00:00.000Z","title":"","descri":"","used_status":""}]
     * Code : 200
     */

    private String Status;
    private String Message;
    private int Code;
    /**
     * coupon_type : NORMAL CODE
     * coupon_code : TRITON123
     * expired_date : 2021-10-08T00:00:00.000Z
     * title :
     * descri :
     * used_status :
     */

    private List<DataBean> Data;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        private String coupon_type;
        private String coupon_code;
        private String expired_date;
        private String title;
        private String descri;
        private String used_status;
        private String coupon_img;

        public String getCoupon_img() {
            return coupon_img;
        }

        public void setCoupon_img(String coupon_img) {
            this.coupon_img = coupon_img;
        }

        public String getCoupon_type() {
            return coupon_type;
        }

        public void setCoupon_type(String coupon_type) {
            this.coupon_type = coupon_type;
        }

        public String getCoupon_code() {
            return coupon_code;
        }

        public void setCoupon_code(String coupon_code) {
            this.coupon_code = coupon_code;
        }

        public String getExpired_date() {
            return expired_date;
        }

        public void setExpired_date(String expired_date) {
            this.expired_date = expired_date;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescri() {
            return descri;
        }

        public void setDescri(String descri) {
            this.descri = descri;
        }

        public String getUsed_status() {
            return used_status;
        }

        public void setUsed_status(String used_status) {
            this.used_status = used_status;
        }
    }
}
