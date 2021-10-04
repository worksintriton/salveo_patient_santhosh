package com.salveo.mysalveo.responsepojo;

import java.util.List;

public class PetLoverVendorOrderListResponse {


    /**
     * Status : Success
     * Message : Petlover Order Grouped
     * Data : [{"p_order_id":"ORDER-1619163505840","p_user_id":"603e27792c2b43125f8cb802","p_shipping_address":"60797c16a20ca32d2668a30c","p_payment_id":"pay_H2Hag4i3PqJZeq","p_vendor_id":"604866a50b3a487571a1c568","p_order_product_count":4,"p_order_price":571,"p_order_image":"http://54.212.108.156:3000/api/uploads/1616648074881.jpeg","p_order_booked_on":"23-04-2021 01:08 PM","p_order_status":"New","p_order_text":"Food Products","p_cancelled_date":"","p_completed_date":"","p_user_feedback":"","p_user_rate":0}]
     * Code : 200
     */

    private String Status;
    private String Message;
    private int Code;
    /**
     * p_order_id : ORDER-1619163505840
     * p_user_id : 603e27792c2b43125f8cb802
     * p_shipping_address : 60797c16a20ca32d2668a30c
     * p_payment_id : pay_H2Hag4i3PqJZeq
     * p_vendor_id : 604866a50b3a487571a1c568
     * p_order_product_count : 4
     * p_order_price : 571
     * p_order_image : http://54.212.108.156:3000/api/uploads/1616648074881.jpeg
     * p_order_booked_on : 23-04-2021 01:08 PM
     * p_order_status : New
     * p_order_text : Food Products
     * p_cancelled_date :
     * p_completed_date :
     * p_user_feedback :
     * p_user_rate : 0
     */

    private List<DataBean> Data;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        private String p_order_id;
        private String p_user_id;
        private String p_shipping_address;
        private String p_payment_id;
        private String p_vendor_id;
        private int p_order_product_count;
        private int p_order_price;
        private String p_order_image;
        private String p_order_booked_on;
        private String p_order_status;
        private String p_order_text;
        private String p_cancelled_date;
        private String p_completed_date;
        private String p_user_feedback;
        private int p_user_rate;

        private String thumbnail_image;
        public String getThumbnail_image() {
            return thumbnail_image;
        }

        public void setThumbnail_image(String thumbnail_image) {
            this.thumbnail_image = thumbnail_image;
        }

        public String getP_order_id() {
            return p_order_id;
        }

        public void setP_order_id(String p_order_id) {
            this.p_order_id = p_order_id;
        }

        public String getP_user_id() {
            return p_user_id;
        }

        public void setP_user_id(String p_user_id) {
            this.p_user_id = p_user_id;
        }

        public String getP_shipping_address() {
            return p_shipping_address;
        }

        public void setP_shipping_address(String p_shipping_address) {
            this.p_shipping_address = p_shipping_address;
        }

        public String getP_payment_id() {
            return p_payment_id;
        }

        public void setP_payment_id(String p_payment_id) {
            this.p_payment_id = p_payment_id;
        }

        public String getP_vendor_id() {
            return p_vendor_id;
        }

        public void setP_vendor_id(String p_vendor_id) {
            this.p_vendor_id = p_vendor_id;
        }

        public int getP_order_product_count() {
            return p_order_product_count;
        }

        public void setP_order_product_count(int p_order_product_count) {
            this.p_order_product_count = p_order_product_count;
        }

        public int getP_order_price() {
            return p_order_price;
        }

        public void setP_order_price(int p_order_price) {
            this.p_order_price = p_order_price;
        }

        public String getP_order_image() {
            return p_order_image;
        }

        public void setP_order_image(String p_order_image) {
            this.p_order_image = p_order_image;
        }

        public String getP_order_booked_on() {
            return p_order_booked_on;
        }

        public void setP_order_booked_on(String p_order_booked_on) {
            this.p_order_booked_on = p_order_booked_on;
        }

        public String getP_order_status() {
            return p_order_status;
        }

        public void setP_order_status(String p_order_status) {
            this.p_order_status = p_order_status;
        }

        public String getP_order_text() {
            return p_order_text;
        }

        public void setP_order_text(String p_order_text) {
            this.p_order_text = p_order_text;
        }

        public String getP_cancelled_date() {
            return p_cancelled_date;
        }

        public void setP_cancelled_date(String p_cancelled_date) {
            this.p_cancelled_date = p_cancelled_date;
        }

        public String getP_completed_date() {
            return p_completed_date;
        }

        public void setP_completed_date(String p_completed_date) {
            this.p_completed_date = p_completed_date;
        }

        public String getP_user_feedback() {
            return p_user_feedback;
        }

        public void setP_user_feedback(String p_user_feedback) {
            this.p_user_feedback = p_user_feedback;
        }

        public int getP_user_rate() {
            return p_user_rate;
        }

        public void setP_user_rate(int p_user_rate) {
            this.p_user_rate = p_user_rate;
        }
    }
}
