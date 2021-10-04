package com.salveo.mysalveo.requestpojo;

public class ProductVendorCreateRequest {

    /**
     * _id : 60a5df0f785e571920ac46f0
     * cost : 200
     * threshould : 20
     * product_discription : this is good
     * date_and_time : Thu Feb 18 2021 15:05:46 GMT+0530 (India Standard Time)
     * vendor_id : 60ae1b6c48ffef65a41bc545
     * mobile_type : Admin
     */

    private String _id;
    private int cost;
    private String threshould;
    private String product_discription;
    private String date_and_time;
    private String vendor_id;
    private String mobile_type;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getThreshould() {
        return threshould;
    }

    public void setThreshould(String threshould) {
        this.threshould = threshould;
    }

    public String getProduct_discription() {
        return product_discription;
    }

    public void setProduct_discription(String product_discription) {
        this.product_discription = product_discription;
    }

    public String getDate_and_time() {
        return date_and_time;
    }

    public void setDate_and_time(String date_and_time) {
        this.date_and_time = date_and_time;
    }

    public String getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(String vendor_id) {
        this.vendor_id = vendor_id;
    }

    public String getMobile_type() {
        return mobile_type;
    }

    public void setMobile_type(String mobile_type) {
        this.mobile_type = mobile_type;
    }
}
