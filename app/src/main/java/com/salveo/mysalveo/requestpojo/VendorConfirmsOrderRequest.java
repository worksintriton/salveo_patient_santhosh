package com.salveo.mysalveo.requestpojo;

public class VendorConfirmsOrderRequest {


    /**
     * _id : 6049e4f564a9296f3d7c3327
     * activity_id : 2
     * activity_title : Order Confirm
     * activity_date : 11-03-2021 03:07 PM
     */

    private String _id;
    private int activity_id;
    private String activity_title;
    private String activity_date;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(int activity_id) {
        this.activity_id = activity_id;
    }

    public String getActivity_title() {
        return activity_title;
    }

    public void setActivity_title(String activity_title) {
        this.activity_title = activity_title;
    }

    public String getActivity_date() {
        return activity_date;
    }

    public void setActivity_date(String activity_date) {
        this.activity_date = activity_date;
    }
}
