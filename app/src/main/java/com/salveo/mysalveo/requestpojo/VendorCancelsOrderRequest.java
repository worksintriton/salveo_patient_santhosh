package com.salveo.mysalveo.requestpojo;

public class VendorCancelsOrderRequest {


    /**
     * _id : 604b387942cb073ec4dfef16
     * activity_id : 7
     * activity_title : Vendor cancelled
     * activity_date : 11-03-2021 03:07 PM
     * order_status : cancelled
     * vendor_cancell_info : We don't have stock in our company
     * vendor_cancell_date : 11-03-2021 03:07 PM
     */

    private String _id;
    private int activity_id;
    private String activity_title;
    private String activity_date;
    private String order_status;
    private String vendor_cancell_info;
    private String vendor_cancell_date;

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

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getVendor_cancell_info() {
        return vendor_cancell_info;
    }

    public void setVendor_cancell_info(String vendor_cancell_info) {
        this.vendor_cancell_info = vendor_cancell_info;
    }

    public String getVendor_cancell_date() {
        return vendor_cancell_date;
    }

    public void setVendor_cancell_date(String vendor_cancell_date) {
        this.vendor_cancell_date = vendor_cancell_date;
    }
}
