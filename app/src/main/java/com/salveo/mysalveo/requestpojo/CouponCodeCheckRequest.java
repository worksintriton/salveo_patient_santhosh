package com.salveo.mysalveo.requestpojo;

public class CouponCodeCheckRequest {

    /**
     * current_date : 10/09/2021
     * total_amount : 10000
     * coupon_type : 2
     * user_id :
     * code : TRITON123
     */

    private String current_date;
    private int total_amount;
    private String coupon_type;
    private String user_id;
    private String code;

    public String getCurrent_date() {
        return current_date;
    }

    public void setCurrent_date(String current_date) {
        this.current_date = current_date;
    }

    public int getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(int total_amount) {
        this.total_amount = total_amount;
    }

    public String getCoupon_type() {
        return coupon_type;
    }

    public void setCoupon_type(String coupon_type) {
        this.coupon_type = coupon_type;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
