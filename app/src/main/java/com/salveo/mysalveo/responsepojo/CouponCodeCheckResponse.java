package com.salveo.mysalveo.responsepojo;

public class CouponCodeCheckResponse {

    /**
     * Status : Success
     * Message : Coupon Code Applied Successfully
     * Data : {"original_price":100,"discount_price":10,"total_price":90}
     * Code : 200
     */

    private String Status;
    private String Message;
    /**
     * original_price : 100
     * discount_price : 10
     * total_price : 90
     */

    private DataBean Data;
    private int Code;

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

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }

    public static class DataBean {
        private int original_price;
        private int discount_price;
        private int total_price;

        public int getOriginal_price() {
            return original_price;
        }

        public void setOriginal_price(int original_price) {
            this.original_price = original_price;
        }

        public int getDiscount_price() {
            return discount_price;
        }

        public void setDiscount_price(int discount_price) {
            this.discount_price = discount_price;
        }

        public int getTotal_price() {
            return total_price;
        }

        public void setTotal_price(int total_price) {
            this.total_price = total_price;
        }
    }
}
