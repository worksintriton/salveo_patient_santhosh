package com.salveo.mysalveo.responsepojo;

public class ShippingAddressFetchByUserIDResponse {

    /**
     * Status : Success
     * Message : Shipping address details
     * Data : {"_id":"608fd03f081f440548fd3ab6","user_id":{"_id":"603e27792c2b43125f8cb802","first_name":"imthi","last_name":"DKK","user_email":"imthiyas@gmail.com","user_phone":"0123456789","date_of_reg":"02/03/2021 05:24 PM","user_type":1,"user_status":"complete","otp":123456,"profile_img":"http://54.212.108.156:3000/api/uploads/1614844333127.jpeg","user_email_verification":false,"fb_token":"clTQH7GbTVGRy-weBGlpgV:APA91bGQvsISuqQC35DxsdUc9oPLcYG3Q5tHYF6HP9MnPHLWImOgmlHdCFnILfpTPoaqPhOgy4a6SS31HXrS5veJgQO_xN9jBmlSDqeGPD_SZHKAtS4uxjpgpGDxB3BRF-fR2XkziCyM","device_id":"","device_type":"","mobile_type":"Android","delete_status":false,"updatedAt":"2021-05-03T07:28:43.544Z","createdAt":"2021-03-02T11:54:33.405Z","__v":0},"location_state":"Tamil Nadu","location_country":"India","location_city":"Iluppaiyur","location_pin":"621006","location_address":"Karatampatti Iluppaiyur road, Iluppaiyur, Tamil Nadu 621006, India","location_lat":11.064702890698873,"location_long":78.6342767626047,"location_title":"Home","location_nickname":"DK HOME","default_status":true,"date_and_time":"03-05-2021 03:58 PM","mobile_type":"Android","delete_status":false,"updatedAt":"2021-05-03T10:28:15.381Z","createdAt":"2021-05-03T10:28:15.381Z","__v":0}
     * Code : 200
     */

    private String Status;
    private String Message;
    /**
     * _id : 608fd03f081f440548fd3ab6
     * user_id : {"_id":"603e27792c2b43125f8cb802","first_name":"imthi","last_name":"DKK","user_email":"imthiyas@gmail.com","user_phone":"0123456789","date_of_reg":"02/03/2021 05:24 PM","user_type":1,"user_status":"complete","otp":123456,"profile_img":"http://54.212.108.156:3000/api/uploads/1614844333127.jpeg","user_email_verification":false,"fb_token":"clTQH7GbTVGRy-weBGlpgV:APA91bGQvsISuqQC35DxsdUc9oPLcYG3Q5tHYF6HP9MnPHLWImOgmlHdCFnILfpTPoaqPhOgy4a6SS31HXrS5veJgQO_xN9jBmlSDqeGPD_SZHKAtS4uxjpgpGDxB3BRF-fR2XkziCyM","device_id":"","device_type":"","mobile_type":"Android","delete_status":false,"updatedAt":"2021-05-03T07:28:43.544Z","createdAt":"2021-03-02T11:54:33.405Z","__v":0}
     * location_state : Tamil Nadu
     * location_country : India
     * location_city : Iluppaiyur
     * location_pin : 621006
     * location_address : Karatampatti Iluppaiyur road, Iluppaiyur, Tamil Nadu 621006, India
     * location_lat : 11.064702890698873
     * location_long : 78.6342767626047
     * location_title : Home
     * location_nickname : DK HOME
     * default_status : true
     * date_and_time : 03-05-2021 03:58 PM
     * mobile_type : Android
     * delete_status : false
     * updatedAt : 2021-05-03T10:28:15.381Z
     * createdAt : 2021-05-03T10:28:15.381Z
     * __v : 0
     */

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

    public static class DataBean {
        private String _id;
        /**
         * _id : 603e27792c2b43125f8cb802
         * first_name : imthi
         * last_name : DKK
         * user_email : imthiyas@gmail.com
         * user_phone : 0123456789
         * date_of_reg : 02/03/2021 05:24 PM
         * user_type : 1
         * user_status : complete
         * otp : 123456
         * profile_img : http://54.212.108.156:3000/api/uploads/1614844333127.jpeg
         * user_email_verification : false
         * fb_token : clTQH7GbTVGRy-weBGlpgV:APA91bGQvsISuqQC35DxsdUc9oPLcYG3Q5tHYF6HP9MnPHLWImOgmlHdCFnILfpTPoaqPhOgy4a6SS31HXrS5veJgQO_xN9jBmlSDqeGPD_SZHKAtS4uxjpgpGDxB3BRF-fR2XkziCyM
         * device_id :
         * device_type :
         * mobile_type : Android
         * delete_status : false
         * updatedAt : 2021-05-03T07:28:43.544Z
         * createdAt : 2021-03-02T11:54:33.405Z
         * __v : 0
         */

        private UserIdBean user_id;
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
        private boolean delete_status;
        private String updatedAt;
        private String createdAt;
        private int __v;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public UserIdBean getUser_id() {
            return user_id;
        }

        public void setUser_id(UserIdBean user_id) {
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

        public boolean isDelete_status() {
            return delete_status;
        }

        public void setDelete_status(boolean delete_status) {
            this.delete_status = delete_status;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public int get__v() {
            return __v;
        }

        public void set__v(int __v) {
            this.__v = __v;
        }

        public static class UserIdBean {
            private String _id;
            private String first_name;
            private String last_name;
            private String user_email;
            private String user_phone;
            private String date_of_reg;
            private int user_type;
            private String user_status;
            private int otp;
            private String profile_img;
            private boolean user_email_verification;
            private String fb_token;
            private String device_id;
            private String device_type;
            private String mobile_type;
            private boolean delete_status;
            private String updatedAt;
            private String createdAt;
            private int __v;

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public String getFirst_name() {
                return first_name;
            }

            public void setFirst_name(String first_name) {
                this.first_name = first_name;
            }

            public String getLast_name() {
                return last_name;
            }

            public void setLast_name(String last_name) {
                this.last_name = last_name;
            }

            public String getUser_email() {
                return user_email;
            }

            public void setUser_email(String user_email) {
                this.user_email = user_email;
            }

            public String getUser_phone() {
                return user_phone;
            }

            public void setUser_phone(String user_phone) {
                this.user_phone = user_phone;
            }

            public String getDate_of_reg() {
                return date_of_reg;
            }

            public void setDate_of_reg(String date_of_reg) {
                this.date_of_reg = date_of_reg;
            }

            public int getUser_type() {
                return user_type;
            }

            public void setUser_type(int user_type) {
                this.user_type = user_type;
            }

            public String getUser_status() {
                return user_status;
            }

            public void setUser_status(String user_status) {
                this.user_status = user_status;
            }

            public int getOtp() {
                return otp;
            }

            public void setOtp(int otp) {
                this.otp = otp;
            }

            public String getProfile_img() {
                return profile_img;
            }

            public void setProfile_img(String profile_img) {
                this.profile_img = profile_img;
            }

            public boolean isUser_email_verification() {
                return user_email_verification;
            }

            public void setUser_email_verification(boolean user_email_verification) {
                this.user_email_verification = user_email_verification;
            }

            public String getFb_token() {
                return fb_token;
            }

            public void setFb_token(String fb_token) {
                this.fb_token = fb_token;
            }

            public String getDevice_id() {
                return device_id;
            }

            public void setDevice_id(String device_id) {
                this.device_id = device_id;
            }

            public String getDevice_type() {
                return device_type;
            }

            public void setDevice_type(String device_type) {
                this.device_type = device_type;
            }

            public String getMobile_type() {
                return mobile_type;
            }

            public void setMobile_type(String mobile_type) {
                this.mobile_type = mobile_type;
            }

            public boolean isDelete_status() {
                return delete_status;
            }

            public void setDelete_status(boolean delete_status) {
                this.delete_status = delete_status;
            }

            public String getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public int get__v() {
                return __v;
            }

            public void set__v(int __v) {
                this.__v = __v;
            }
        }
    }
}