package com.salveo.mysalveo.responsepojo;

import java.util.List;

public class VendorOrderListResponse {


    /**
     * Status : Success
     * Message : Vendor Order Grouped
     * Data : [{"v_order_id":"ORDER-1619163505840","v_user_id":"603e27792c2b43125f8cb802","v_shipping_address":"60797c16a20ca32d2668a30c","v_payment_id":"pay_H2Hag4i3PqJZeq","v_vendor_id":"604866a50b3a487571a1c568","v_order_product_count":4,"v_order_price":571,"v_order_image":"http://54.212.108.156:3000/api/uploads/1616648074881.jpeg","v_order_booked_on":"23-04-2021 01:08 PM","v_order_status":"New","v_order_text":"Food Products","v_cancelled_date":"","v_completed_date":"","v_user_feedback":"","v_user_rate":0}]
     * Code : 200
     */

    private String Status;
    private String Message;
    private int Code;
    /**
     * v_order_id : ORDER-1619163505840
     * v_user_id : 603e27792c2b43125f8cb802
     * v_shipping_address : 60797c16a20ca32d2668a30c
     * v_payment_id : pay_H2Hag4i3PqJZeq
     * v_vendor_id : 604866a50b3a487571a1c568
     * v_order_product_count : 4
     * v_order_price : 571
     * v_order_image : http://54.212.108.156:3000/api/uploads/1616648074881.jpeg
     * v_order_booked_on : 23-04-2021 01:08 PM
     * v_order_status : New
     * v_order_text : Food Products
     * v_cancelled_date :
     * v_completed_date :
     * v_user_feedback :
     * v_user_rate : 0
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
        private String v_order_id;
        private String v_user_id;
        private String v_shipping_address;
        private String v_payment_id;
        private String v_vendor_id;
        private int v_order_product_count;
        private int v_order_price;
        private String v_order_image;
        private String v_order_booked_on;
        private String v_order_status;
        private String v_order_text;
        private String v_cancelled_date;
        private String v_completed_date;
        private String v_user_feedback;
        private int v_user_rate;

        public String getV_order_id() {
            return v_order_id;
        }

        public void setV_order_id(String v_order_id) {
            this.v_order_id = v_order_id;
        }

        public String getV_user_id() {
            return v_user_id;
        }

        public void setV_user_id(String v_user_id) {
            this.v_user_id = v_user_id;
        }

        public String getV_shipping_address() {
            return v_shipping_address;
        }

        public void setV_shipping_address(String v_shipping_address) {
            this.v_shipping_address = v_shipping_address;
        }

        public String getV_payment_id() {
            return v_payment_id;
        }

        public void setV_payment_id(String v_payment_id) {
            this.v_payment_id = v_payment_id;
        }

        public String getV_vendor_id() {
            return v_vendor_id;
        }

        public void setV_vendor_id(String v_vendor_id) {
            this.v_vendor_id = v_vendor_id;
        }

        public int getV_order_product_count() {
            return v_order_product_count;
        }

        public void setV_order_product_count(int v_order_product_count) {
            this.v_order_product_count = v_order_product_count;
        }

        public int getV_order_price() {
            return v_order_price;
        }

        public void setV_order_price(int v_order_price) {
            this.v_order_price = v_order_price;
        }

        public String getV_order_image() {
            return v_order_image;
        }

        public void setV_order_image(String v_order_image) {
            this.v_order_image = v_order_image;
        }

        public String getV_order_booked_on() {
            return v_order_booked_on;
        }

        public void setV_order_booked_on(String v_order_booked_on) {
            this.v_order_booked_on = v_order_booked_on;
        }

        public String getV_order_status() {
            return v_order_status;
        }

        public void setV_order_status(String v_order_status) {
            this.v_order_status = v_order_status;
        }

        public String getV_order_text() {
            return v_order_text;
        }

        public void setV_order_text(String v_order_text) {
            this.v_order_text = v_order_text;
        }

        public String getV_cancelled_date() {
            return v_cancelled_date;
        }

        public void setV_cancelled_date(String v_cancelled_date) {
            this.v_cancelled_date = v_cancelled_date;
        }

        public String getV_completed_date() {
            return v_completed_date;
        }

        public void setV_completed_date(String v_completed_date) {
            this.v_completed_date = v_completed_date;
        }

        public String getV_user_feedback() {
            return v_user_feedback;
        }

        public void setV_user_feedback(String v_user_feedback) {
            this.v_user_feedback = v_user_feedback;
        }

        public int getV_user_rate() {
            return v_user_rate;
        }

        public void setV_user_rate(int v_user_rate) {
            this.v_user_rate = v_user_rate;
        }
    }
}
