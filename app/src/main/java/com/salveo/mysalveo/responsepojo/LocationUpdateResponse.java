package com.salveo.mysalveo.responsepojo;

public class LocationUpdateResponse {

    /**
     * Status : Success
     * Message : Location Updated
     * Data : {"_id":"5fcf09c3928d5f5634501b35","user_id":"5fc61b82b750da703e48da78","location_state":"asdfasdfasd","location_country":"asdfasdfasd","location_city":"asdfasdfasd","location_pin":"asdfasdfasd","location_address":"asdfasdfasd","location_lat":18.90123,"location_long":12.09123,"location_title":"23-10-1996 12:09 AM","location_nickname":"123","default_status":false,"date_and_time":"23-10-1996 12:09 AM","mobile_type":"Android","__v":0}
     * Code : 200
     */

    private String Status;
    private String Message;
    private DataBean Data;
    private int Code;


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

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }

    public static class DataBean  {
        /**
         * _id : 5fcf09c3928d5f5634501b35
         * user_id : 5fc61b82b750da703e48da78
         * location_state : asdfasdfasd
         * location_country : asdfasdfasd
         * location_city : asdfasdfasd
         * location_pin : asdfasdfasd
         * location_address : asdfasdfasd
         * location_lat : 18.90123
         * location_long : 12.09123
         * location_title : 23-10-1996 12:09 AM
         * location_nickname : 123
         * default_status : false
         * date_and_time : 23-10-1996 12:09 AM
         * mobile_type : Android
         * __v : 0
         */

        private String _id;
        private String user_id;
        private String location_state;
        private String location_country;
        private String location_city;
        private String location_pin;
        private String location_address;
        private double location_lat;
        private double location_long;
        private String location_title;
        private String location_nickname;
        private boolean default_status;
        private String date_and_time;
        private String mobile_type;
        private int __v;


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

        public String getLocation_state() {
            return location_state;
        }

        public void setLocation_state(String location_state) {
            this.location_state = location_state;
        }

        public String getLocation_country() {
            return location_country;
        }

        public void setLocation_country(String location_country) {
            this.location_country = location_country;
        }

        public String getLocation_city() {
            return location_city;
        }

        public void setLocation_city(String location_city) {
            this.location_city = location_city;
        }

        public String getLocation_pin() {
            return location_pin;
        }

        public void setLocation_pin(String location_pin) {
            this.location_pin = location_pin;
        }

        public String getLocation_address() {
            return location_address;
        }

        public void setLocation_address(String location_address) {
            this.location_address = location_address;
        }

        public double getLocation_lat() {
            return location_lat;
        }

        public void setLocation_lat(double location_lat) {
            this.location_lat = location_lat;
        }

        public double getLocation_long() {
            return location_long;
        }

        public void setLocation_long(double location_long) {
            this.location_long = location_long;
        }

        public String getLocation_title() {
            return location_title;
        }

        public void setLocation_title(String location_title) {
            this.location_title = location_title;
        }

        public String getLocation_nickname() {
            return location_nickname;
        }

        public void setLocation_nickname(String location_nickname) {
            this.location_nickname = location_nickname;
        }

        public boolean isDefault_status() {
            return default_status;
        }

        public void setDefault_status(boolean default_status) {
            this.default_status = default_status;
        }

        public String getDate_and_time() {
            return date_and_time;
        }

        public void setDate_and_time(String date_and_time) {
            this.date_and_time = date_and_time;
        }

        public String getMobile_type() {
            return mobile_type;
        }

        public void setMobile_type(String mobile_type) {
            this.mobile_type = mobile_type;
        }

        public int get__v() {
            return __v;
        }

        public void set__v(int __v) {
            this.__v = __v;
        }
    }
}
