package com.salveo.mysalveo.responsepojo;

import java.util.List;

public class NotificationGetlistResponse {


    /**
     * Status : Success
     * Message : Notification List
     * Data : [{"_id":"5fb8ce5f3d17401d9a8e7f70","user_id":"5fb8b160e0ba3b0e6268dd2e","notify_title":"Testins","notify_descri":"Testins","notify_img":"","notify_time":"","date_and_time":"23-10-2029 11:00 AM","__v":0},{"_id":"5fb8ce713d17401d9a8e7f71","user_id":"5fb8b160e0ba3b0e6268dd2e","notify_title":"Testins","notify_descri":"Testins","notify_img":"","notify_time":"","date_and_time":"23-10-2029 11:00 AM","__v":0},{"_id":"5fb8ce723d17401d9a8e7f72","user_id":"5fb8b160e0ba3b0e6268dd2e","notify_title":"Testins","notify_descri":"Testins","notify_img":"","notify_time":"","date_and_time":"23-10-2029 11:00 AM","__v":0},{"_id":"5fb8ce733d17401d9a8e7f73","user_id":"5fb8b160e0ba3b0e6268dd2e","notify_title":"Testins","notify_descri":"Testins","notify_img":"","notify_time":"","date_and_time":"23-10-2029 11:00 AM","__v":0},{"_id":"5fb8ce743d17401d9a8e7f74","user_id":"5fb8b160e0ba3b0e6268dd2e","notify_title":"Testins","notify_descri":"Testins","notify_img":"","notify_time":"","date_and_time":"23-10-2029 11:00 AM","__v":0}]
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
         * _id : 5fb8ce5f3d17401d9a8e7f70
         * user_id : 5fb8b160e0ba3b0e6268dd2e
         * notify_title : Testins
         * notify_descri : Testins
         * notify_img :
         * notify_time :
         * date_and_time : 23-10-2029 11:00 AM
         * __v : 0
         */

        private String _id;
        private String user_id;
        private String notify_title;
        private String notify_descri;
        private String notify_img;
        private String notify_time;
        private String date_and_time;
        private int __v;


        public DataBean(String _id, String user_id, String notify_title, String notify_descri, String notify_img, String notify_time, String date_and_time) {
            this._id = _id;
            this.user_id = user_id;
            this.notify_title = notify_title;
            this.notify_descri = notify_descri;
            this.notify_img = notify_img;
            this.notify_time = notify_time;
            this.date_and_time = date_and_time;
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


        public String getNotify_title() {
            return notify_title;
        }

        public void setNotify_title(String notify_title) {
            this.notify_title = notify_title;

        }


        public String getNotify_descri() {
            return notify_descri;
        }

        public void setNotify_descri(String notify_descri) {
            this.notify_descri = notify_descri;

        }


        public String getNotify_img() {
            return notify_img;
        }

        public void setNotify_img(String notify_img) {
            this.notify_img = notify_img;

        }

        public String getNotify_time() {
            return notify_time;
        }

        public void setNotify_time(String notify_time) {
            this.notify_time = notify_time;

        }


        public String getDate_and_time() {
            return date_and_time;
        }

        public void setDate_and_time(String date_and_time) {
            this.date_and_time = date_and_time;

        }


        public int get__v() {
            return __v;
        }

        public void set__v(int __v) {
            this.__v = __v;

        }
    }
}
