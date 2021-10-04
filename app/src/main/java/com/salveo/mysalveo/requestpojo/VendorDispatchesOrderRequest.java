package com.salveo.mysalveo.requestpojo;

public class VendorDispatchesOrderRequest {


    /**
     * _id : 604b10cc8788633a05dbf018
     * activity_id : 3
     * activity_title : Order Dispatch
     * activity_date : 12-03-2021 12:27 PM
     * vendor_complete_date : 12-03-2021 12:35 PM
     * vendor_complete_info : Tracking-Id : 1234568, You can check the product taacking witn this id
     * order_status : Complete
     */

    private String _id;
    private int activity_id;
    private String activity_title;
    private String activity_date;
    private String vendor_complete_date;
    private String vendor_complete_info;
    private String order_status;

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

    public String getVendor_complete_date() {
        return vendor_complete_date;
    }

    public void setVendor_complete_date(String vendor_complete_date) {
        this.vendor_complete_date = vendor_complete_date;
    }

    public String getVendor_complete_info() {
        return vendor_complete_info;
    }

    public void setVendor_complete_info(String vendor_complete_info) {
        this.vendor_complete_info = vendor_complete_info;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }
}
