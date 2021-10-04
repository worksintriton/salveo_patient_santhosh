package com.salveo.mysalveo.requestpojo;

public class ApplySingleDiscountCalRequest {


    /**
     * _id : 605328895e35b95a5cf804e4
     * discount_status : false
     * discount_amount : 2
     * discount : 0
     */

    private String _id;
    private boolean discount_status;
    private int discount_amount;
    private int discount;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public boolean isDiscount_status() {
        return discount_status;
    }

    public void setDiscount_status(boolean discount_status) {
        this.discount_status = discount_status;
    }

    public int getDiscount_amount() {
        return discount_amount;
    }

    public void setDiscount_amount(int discount_amount) {
        this.discount_amount = discount_amount;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
