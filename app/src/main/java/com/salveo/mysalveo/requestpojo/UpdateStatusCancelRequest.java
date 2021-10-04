package com.salveo.mysalveo.requestpojo;

public class UpdateStatusCancelRequest {

    /**
     * _id : 604f4386a358454d3208f685
     * activity_id : 4
     * activity_title : Order Cancelled
     * activity_date : 11-03-2021 03:07 PM
     * order_status : Cancelled
     * user_cancell_info : I have order wrongly
     * user_cancell_date : 11-03-2021 03:07 PM
     */

    private String _id;
    private int activity_id;
    private String activity_title;
    private String activity_date;
    private String order_status;
    private String user_cancell_info;
    private String user_cancell_date;


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


    public String getUser_cancell_info() {
        return user_cancell_info;
    }

    public void setUser_cancell_info(String user_cancell_info) {
        this.user_cancell_info = user_cancell_info;

    }

    public String getUser_cancell_date() {
        return user_cancell_date;
    }

    public void setUser_cancell_date(String user_cancell_date) {
        this.user_cancell_date = user_cancell_date;
    }
}
