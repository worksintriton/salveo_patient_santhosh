package com.salveo.mysalveo.responsepojo;

import java.util.List;

public class HolidayListResponse {

    /**
     * Status : Success
     * Message : doctor holiday list
     * Data : [{"_id":"5f88587983b6d9067d05e1dd","Email_id":"mohammedimthi2395@gmail.com","Date":"23-10-2020","__v":0}]
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
         * _id : 5f88587983b6d9067d05e1dd
         * Email_id : mohammedimthi2395@gmail.com
         * Date : 23-10-2020
         * __v : 0
         */

        private String _id;
        private String Email_id;
        private String Date;
        private int __v;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getEmail_id() {
            return Email_id;
        }

        public void setEmail_id(String Email_id) {
            this.Email_id = Email_id;
        }

        public String getDate() {
            return Date;
        }

        public void setDate(String Date) {
            this.Date = Date;
        }

        public int get__v() {
            return __v;
        }

        public void set__v(int __v) {
            this.__v = __v;
        }
    }
}
