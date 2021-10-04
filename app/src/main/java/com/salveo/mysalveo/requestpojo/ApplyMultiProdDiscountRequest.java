package com.salveo.mysalveo.requestpojo;

import java.util.List;

public class ApplyMultiProdDiscountRequest {


    /**
     * _id : ["604b34a242cb073ec4dfef12","604b34cb42cb073ec4dfef13"]
     * discount_status : false
     * discount_amount : 2
     * discount : 0
     *discount_start_date : "23-03-2021",
     *discount_end_date : "24-02-2021"
     */

    private boolean discount_status;
    private int discount_amount;
    private int discount;
    private List<String> _id;
    private String discount_start_date;
    private String discount_end_date;

    public String getDiscount_start_date() {
        return discount_start_date;
    }

    public void setDiscount_start_date(String discount_start_date) {
        this.discount_start_date = discount_start_date;
    }

    public String getDiscount_end_date() {
        return discount_end_date;
    }

    public void setDiscount_end_date(String discount_end_date) {
        this.discount_end_date = discount_end_date;
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

    public List<String> get_id() {
        return _id;
    }

    public void set_id(List<String> _id) {
        this._id = _id;
    }
}
