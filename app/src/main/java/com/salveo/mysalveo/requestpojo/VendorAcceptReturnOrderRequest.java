package com.salveo.mysalveo.requestpojo;

public class VendorAcceptReturnOrderRequest {


    /**
     * _id : 6053b5e0d7570364e4d28c98
     * activity_id : 6
     * activity_title : Vendor Accept Return
     * activity_date : 11-03-2021 03:07 PM
     * vendor_accept_cancel : Ok I will accept the Return
     * vendor_accept_cancel_date : 11-03-2021 03:07 PM
     */

    private String _id;
    private int activity_id;
    private String activity_title;
    private String activity_date;
    private String vendor_accept_cancel;
    private String vendor_accept_cancel_date;

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

    public String getVendor_accept_cancel() {
        return vendor_accept_cancel;
    }

    public void setVendor_accept_cancel(String vendor_accept_cancel) {
        this.vendor_accept_cancel = vendor_accept_cancel;
    }

    public String getVendor_accept_cancel_date() {
        return vendor_accept_cancel_date;
    }

    public void setVendor_accept_cancel_date(String vendor_accept_cancel_date) {
        this.vendor_accept_cancel_date = vendor_accept_cancel_date;
    }
}
