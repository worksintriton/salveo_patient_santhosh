package com.salveo.mysalveo.requestpojo;

public class ProductEditRequest {

    /**
     * _id : 6034d66598fa826140f6a3a3
     * cost : 100
     * threshould : 100
     * product_name : Cat Food
     * product_discription : This is cat lunch.......
     */

    private String _id;
    private int cost;
    private String threshould;
    private String product_name;
    private String product_discription;

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

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_discription() {
        return product_discription;
    }

    public void setProduct_discription(String product_discription) {
        this.product_discription = product_discription;
    }
}
