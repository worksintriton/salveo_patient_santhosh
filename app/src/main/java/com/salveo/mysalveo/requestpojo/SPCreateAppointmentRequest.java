package com.salveo.mysalveo.requestpojo;

import java.util.List;

public class SPCreateAppointmentRequest {

    /**
     * sp_id : 5ff7ef9b1c72093650a13a10
     * booking_date : 23/10/2020
     * booking_time : 12:00 AM
     * booking_date_time : 23/10/2020 12:00 AM
     * user_id : 5fd841a67aa4cc1c6a1e5636
     * pet_id : 5fdc46be1e5d8b0eb31c3699
     * additional_info : this if is for the comments
     * sp_attched : []
     * sp_feedback :
     * sp_rate :
     * user_feedback :
     * user_rate :
     * display_date : 23/10/2020 10:10 AM
     * server_date_time : 23/10/2020 10:10 AM
     * payment_id : 12345
     * payment_method : Card
     * service_name : Grooming
     * service_amount : 200
     * service_time : 15 mins
     * completed_at :
     * missed_at :
     * mobile_type : Admin
     * sp_business_info : []
     * health_issue_title
     */

    private String sp_id;
    private String booking_date;
    private String booking_time;
    private String booking_date_time;
    private String user_id;
    private String patient_id;
    private String additional_info;
    private String sp_feedback;
    private String sp_rate;
    private String user_feedback;
    private String user_rate;
    private String display_date;
    private String server_date_time;
    private String payment_id;
    private String payment_method;
    private String service_name;
    private String service_amount;
    private String service_time;
    private String completed_at;
    private String missed_at;
    private String mobile_type;
    private List<SpAttchedBean> sp_attched;
    private String date_and_time;
    private String health_issue_title;

    private int original_price;
    private int discount_price;
    private int total_price;
    private String coupon_status;
    private String coupon_code;

    public int getOriginal_price() {
        return original_price;
    }

    public void setOriginal_price(int original_price) {
        this.original_price = original_price;
    }

    public int getDiscount_price() {
        return discount_price;
    }

    public void setDiscount_price(int discount_price) {
        this.discount_price = discount_price;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public String getCoupon_status() {
        return coupon_status;
    }

    public void setCoupon_status(String coupon_status) {
        this.coupon_status = coupon_status;
    }

    public String getCoupon_code() {
        return coupon_code;
    }

    public void setCoupon_code(String coupon_code) {
        this.coupon_code = coupon_code;
    }

    public String getHealth_issue_title() {
        return health_issue_title;
    }

    public void setHealth_issue_title(String health_issue_title) {
        this.health_issue_title = health_issue_title;
    }

    public String getDate_and_time() {
        return date_and_time;
    }

    public void setDate_and_time(String date_and_time) {
        this.date_and_time = date_and_time;
    }


    public List<SpAttchedBean> getSp_attched() {
        return sp_attched;
    }

    public void setSp_attched(List<SpAttchedBean> sp_attched) {
        this.sp_attched = sp_attched;
    }

    public String getSp_id() {
        return sp_id;
    }

    public void setSp_id(String sp_id) {
        this.sp_id = sp_id;

    }


    public String getBooking_date() {
        return booking_date;
    }

    public void setBooking_date(String booking_date) {
        this.booking_date = booking_date;

    }


    public String getBooking_time() {
        return booking_time;
    }

    public void setBooking_time(String booking_time) {
        this.booking_time = booking_time;

    }


    public String getBooking_date_time() {
        return booking_date_time;
    }

    public void setBooking_date_time(String booking_date_time) {
        this.booking_date_time = booking_date_time;

    }


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }


    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public String getAdditional_info() {
        return additional_info;
    }

    public void setAdditional_info(String additional_info) {
        this.additional_info = additional_info;

    }


    public String getSp_feedback() {
        return sp_feedback;
    }

    public void setSp_feedback(String sp_feedback) {
        this.sp_feedback = sp_feedback;

    }


    public String getSp_rate() {
        return sp_rate;
    }

    public void setSp_rate(String sp_rate) {
        this.sp_rate = sp_rate;

    }


    public String getUser_feedback() {
        return user_feedback;
    }

    public void setUser_feedback(String user_feedback) {
        this.user_feedback = user_feedback;

    }


    public String getUser_rate() {
        return user_rate;
    }

    public void setUser_rate(String user_rate) {
        this.user_rate = user_rate;

    }

    public String getDisplay_date() {
        return display_date;
    }

    public void setDisplay_date(String display_date) {
        this.display_date = display_date;

    }

    public String getServer_date_time() {
        return server_date_time;
    }

    public void setServer_date_time(String server_date_time) {
        this.server_date_time = server_date_time;

    }


    public String getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(String payment_id) {
        this.payment_id = payment_id;

    }


    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;

    }


    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;

    }


    public String getService_amount() {
        return service_amount;
    }

    public void setService_amount(String service_amount) {
        this.service_amount = service_amount;

    }


    public String getService_time() {
        return service_time;
    }

    public void setService_time(String service_time) {
        this.service_time = service_time;

    }


    public String getCompleted_at() {
        return completed_at;
    }

    public void setCompleted_at(String completed_at) {
        this.completed_at = completed_at;

    }


    public String getMissed_at() {
        return missed_at;
    }

    public void setMissed_at(String missed_at) {
        this.missed_at = missed_at;

    }


    public String getMobile_type() {
        return mobile_type;
    }

    public void setMobile_type(String mobile_type) {
        this.mobile_type = mobile_type;

    }
    public static class SpAttchedBean {
        /**
         * file : http://google.pdf
         */

        private String file;


        public String getFile() {
            return file;
        }

        public void setFile(String file) {
            this.file = file;

        }
    }




}
