package com.salveo.mysalveo.requestpojo;

public class UpdateStatusReturnRequest {

    /**
     * _id : 6049e4f564a9296f3d7c3327
     * activity_id : 5
     * activity_title : Order Returned
     * activity_date : 11-03-2021 03:07 PM
     * order_status : Cancelled
     * user_return_info : the package was damage
     * user_return_date : 11-03-2021 03:07 PM
     * user_return_pic : http://pic.png
     */

    private String _id;
    private int activity_id;
    private String activity_title;
    private String activity_date;
    private String order_status;
    private String user_return_info;
    private String user_return_date;
    private String user_return_pic;




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

    public String getUser_return_info() {
        return user_return_info;
    }

    public void setUser_return_info(String user_return_info) {
        this.user_return_info = user_return_info;

    }


    public String getUser_return_date() {
        return user_return_date;
    }

    public void setUser_return_date(String user_return_date) {
        this.user_return_date = user_return_date;

    }


    public String getUser_return_pic() {
        return user_return_pic;
    }

    public void setUser_return_pic(String user_return_pic) {
        this.user_return_pic = user_return_pic;
    }
}
