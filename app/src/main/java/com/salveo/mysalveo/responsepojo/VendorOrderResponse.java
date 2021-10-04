package com.salveo.mysalveo.responsepojo;

import java.util.List;

public class VendorOrderResponse {

    /**
     * Status : Success
     * Message : New Order List
     * Data : [{"_id":"602b925af36a6b2ce176ff32","user_id":"6025040ee15519672cd0dc02","location_id":"6025045de15519672cd0dc06","order_id":"ORD-16_004","order_title":"Materials","order_booked_at":"16-02-2021 02:55 PM","order_deliver_date":"18-02-2021 02:55 PM","order_final_amount":"10"}]
     * Code : 200
     */

    private String Status;
    private String Message;
    private int Code;
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
        /**
         * _id : 602b925af36a6b2ce176ff32
         * user_id : 6025040ee15519672cd0dc02
         * location_id : 6025045de15519672cd0dc06
         * order_id : ORD-16_004
         * order_title : Materials
         * order_booked_at : 16-02-2021 02:55 PM
         * order_deliver_date : 18-02-2021 02:55 PM
         * order_final_amount : 10
         */

        private String _id;
        private String user_id;
        private String location_id;
        private String order_id;
        private String order_title;
        private String order_booked_at;
        private String order_deliver_date;
        private String order_final_amount;
        private String order_item_count;

        public String getOrder_item_count() {
            return order_item_count;
        }

        public void setOrder_item_count(String order_item_count) {
            this.order_item_count = order_item_count;
        }

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }


        public String getLocation_id() {
            return location_id;
        }

        public void setLocation_id(String location_id) {
            this.location_id = location_id;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getOrder_title() {
            return order_title;
        }

        public void setOrder_title(String order_title) {
            this.order_title = order_title;
        }


        public String getOrder_booked_at() {
            return order_booked_at;
        }

        public void setOrder_booked_at(String order_booked_at) {
            this.order_booked_at = order_booked_at;
        }

        public String getOrder_deliver_date() {
            return order_deliver_date;
        }

        public void setOrder_deliver_date(String order_deliver_date) {
            this.order_deliver_date = order_deliver_date;
        }


        public String getOrder_final_amount() {
            return order_final_amount;
        }

        public void setOrder_final_amount(String order_final_amount) {
            this.order_final_amount = order_final_amount;
        }
    }
}
