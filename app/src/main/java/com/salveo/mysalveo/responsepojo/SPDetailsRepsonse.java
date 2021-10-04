package com.salveo.mysalveo.responsepojo;

import java.util.List;

public class SPDetailsRepsonse {


    /**
     * Status : Success
     * Message : SP Details
     * Data : {"bus_service_list":[{"amount":20,"time_slots":"30 mins","bus_service_list":" Dog Walking"}],"bus_spec_list":[{"bus_spec_list":"Specialization - 1"},{"bus_spec_list":"Specialization - 2"}],"bus_service_gall":[{"bus_service_gall":"http://54.212.108.156:3000/api/uploads/1614685509822.603e24ef2c2b43125f8cb7fe0203171509"}],"bus_certif":[{"bus_certif":"http://54.212.108.156:3000/api/uploads/1614685525491.pdf"}],"_id":"603e25562c2b43125f8cb7ff","user_id":"603e24ef2c2b43125f8cb7fe","bus_user_name":"jack","bus_user_email":"","bussiness_name":"sparrow service","bus_user_phone":"7417417415","bus_profile":"http://54.212.108.156:3000/api/uploads/1614685518352.pdf","bus_proof":"http://54.212.108.156:3000/api/uploads/1614685518352.pdf","date_and_time":"30-04-2021 12:46 PM","mobile_type":"IOS","profile_status":true,"profile_verification_status":"Verified","sp_loc":"Gajendra Circle Bus Stop, Indian Institute Of Technology, Chennai, Tamil Nadu 600036, India","sp_lat":12.991730767308209,"sp_long":80.23317474871874,"delete_status":false,"updatedAt":"2021-04-30T07:20:41.832Z","createdAt":"2021-03-02T11:45:26.472Z","__v":0,"distance":0,"rating":0,"comments":0,"fav":true}
     * Details : {"_id":"5fe185d61996f651f5133693","image_path":"http://54.212.108.156:3000/api/uploads/icon6.jpg","title":" Dog Walking","count":0,"amount":200,"time":"15 mins"}
     * Code : 200
     */

    private String Status;
    private String Message;
    /**
     * bus_service_list : [{"amount":20,"time_slots":"30 mins","bus_service_list":" Dog Walking"}]
     * bus_spec_list : [{"bus_spec_list":"Specialization - 1"},{"bus_spec_list":"Specialization - 2"}]
     * bus_service_gall : [{"bus_service_gall":"http://54.212.108.156:3000/api/uploads/1614685509822.603e24ef2c2b43125f8cb7fe0203171509"}]
     * bus_certif : [{"bus_certif":"http://54.212.108.156:3000/api/uploads/1614685525491.pdf"}]
     * _id : 603e25562c2b43125f8cb7ff
     * user_id : 603e24ef2c2b43125f8cb7fe
     * bus_user_name : jack
     * bus_user_email :
     * bussiness_name : sparrow service
     * bus_user_phone : 7417417415
     * bus_profile : http://54.212.108.156:3000/api/uploads/1614685518352.pdf
     * bus_proof : http://54.212.108.156:3000/api/uploads/1614685518352.pdf
     * date_and_time : 30-04-2021 12:46 PM
     * mobile_type : IOS
     * profile_status : true
     * profile_verification_status : Verified
     * sp_loc : Gajendra Circle Bus Stop, Indian Institute Of Technology, Chennai, Tamil Nadu 600036, India
     * sp_lat : 12.991730767308209
     * sp_long : 80.23317474871874
     * delete_status : false
     * updatedAt : 2021-04-30T07:20:41.832Z
     * createdAt : 2021-03-02T11:45:26.472Z
     * __v : 0
     * distance : 0
     * rating : 0
     * comments : 0
     * fav : true
     */

    private DataBean Data;
    /**
     * _id : 5fe185d61996f651f5133693
     * image_path : http://54.212.108.156:3000/api/uploads/icon6.jpg
     * title :  Dog Walking
     * count : 0
     * amount : 200
     * time : 15 mins
     */

    private DetailsBean Details;
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

    public DetailsBean getDetails() {
        return Details;
    }

    public void setDetails(DetailsBean Details) {
        this.Details = Details;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }

    public static class DataBean {
        private String _id;
        private String user_id;
        private String bus_user_name;
        private String bus_user_email;
        private String bussiness_name;
        private String bus_user_phone;
        private String bus_profile;
        private String bus_proof;
        private String date_and_time;
        private String mobile_type;
        private boolean profile_status;
        private String profile_verification_status;
        private String sp_loc;
        private double sp_lat;
        private double sp_long;
        private boolean delete_status;
        private String updatedAt;
        private String createdAt;
        private int __v;
        private int distance;
        private int rating;
        private int comments;
        private boolean fav;
        /**
         * amount : 20
         * time_slots : 30 mins
         * bus_service_list :  Dog Walking
         */

        private List<BusServiceListBean> bus_service_list;
        /**
         * bus_spec_list : Specialization - 1
         */

        private List<BusSpecListBean> bus_spec_list;
        /**
         * bus_service_gall : http://54.212.108.156:3000/api/uploads/1614685509822.603e24ef2c2b43125f8cb7fe0203171509
         */

        private List<BusServiceGallBean> bus_service_gall;
        /**
         * bus_certif : http://54.212.108.156:3000/api/uploads/1614685525491.pdf
         */

        private List<BusCertifBean> bus_certif;

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

        public String getBus_user_name() {
            return bus_user_name;
        }

        public void setBus_user_name(String bus_user_name) {
            this.bus_user_name = bus_user_name;
        }

        public String getBus_user_email() {
            return bus_user_email;
        }

        public void setBus_user_email(String bus_user_email) {
            this.bus_user_email = bus_user_email;
        }

        public String getBussiness_name() {
            return bussiness_name;
        }

        public void setBussiness_name(String bussiness_name) {
            this.bussiness_name = bussiness_name;
        }

        public String getBus_user_phone() {
            return bus_user_phone;
        }

        public void setBus_user_phone(String bus_user_phone) {
            this.bus_user_phone = bus_user_phone;
        }

        public String getBus_profile() {
            return bus_profile;
        }

        public void setBus_profile(String bus_profile) {
            this.bus_profile = bus_profile;
        }

        public String getBus_proof() {
            return bus_proof;
        }

        public void setBus_proof(String bus_proof) {
            this.bus_proof = bus_proof;
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

        public boolean isProfile_status() {
            return profile_status;
        }

        public void setProfile_status(boolean profile_status) {
            this.profile_status = profile_status;
        }

        public String getProfile_verification_status() {
            return profile_verification_status;
        }

        public void setProfile_verification_status(String profile_verification_status) {
            this.profile_verification_status = profile_verification_status;
        }

        public String getSp_loc() {
            return sp_loc;
        }

        public void setSp_loc(String sp_loc) {
            this.sp_loc = sp_loc;
        }

        public double getSp_lat() {
            return sp_lat;
        }

        public void setSp_lat(double sp_lat) {
            this.sp_lat = sp_lat;
        }

        public double getSp_long() {
            return sp_long;
        }

        public void setSp_long(double sp_long) {
            this.sp_long = sp_long;
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

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public int getRating() {
            return rating;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }

        public int getComments() {
            return comments;
        }

        public void setComments(int comments) {
            this.comments = comments;
        }

        public boolean isFav() {
            return fav;
        }

        public void setFav(boolean fav) {
            this.fav = fav;
        }

        public List<BusServiceListBean> getBus_service_list() {
            return bus_service_list;
        }

        public void setBus_service_list(List<BusServiceListBean> bus_service_list) {
            this.bus_service_list = bus_service_list;
        }

        public List<BusSpecListBean> getBus_spec_list() {
            return bus_spec_list;
        }

        public void setBus_spec_list(List<BusSpecListBean> bus_spec_list) {
            this.bus_spec_list = bus_spec_list;
        }

        public List<BusServiceGallBean> getBus_service_gall() {
            return bus_service_gall;
        }

        public void setBus_service_gall(List<BusServiceGallBean> bus_service_gall) {
            this.bus_service_gall = bus_service_gall;
        }

        public List<BusCertifBean> getBus_certif() {
            return bus_certif;
        }

        public void setBus_certif(List<BusCertifBean> bus_certif) {
            this.bus_certif = bus_certif;
        }

        public static class BusServiceListBean {
            private int amount;
            private String time_slots;
            private String bus_service_list;

            public int getAmount() {
                return amount;
            }

            public void setAmount(int amount) {
                this.amount = amount;
            }

            public String getTime_slots() {
                return time_slots;
            }

            public void setTime_slots(String time_slots) {
                this.time_slots = time_slots;
            }

            public String getBus_service_list() {
                return bus_service_list;
            }

            public void setBus_service_list(String bus_service_list) {
                this.bus_service_list = bus_service_list;
            }
        }

        public static class BusSpecListBean {
            private String bus_spec_list;

            public String getBus_spec_list() {
                return bus_spec_list;
            }

            public void setBus_spec_list(String bus_spec_list) {
                this.bus_spec_list = bus_spec_list;
            }
        }

        public static class BusServiceGallBean {
            private String bus_service_gall;

            public String getBus_service_gall() {
                return bus_service_gall;
            }

            public void setBus_service_gall(String bus_service_gall) {
                this.bus_service_gall = bus_service_gall;
            }
        }

        public static class BusCertifBean {
            private String bus_certif;

            public String getBus_certif() {
                return bus_certif;
            }

            public void setBus_certif(String bus_certif) {
                this.bus_certif = bus_certif;
            }
        }
    }

    public static class DetailsBean {
        private String _id;
        private String image_path;
        private String title;
        private int count;
        private int amount;
        private String time;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getImage_path() {
            return image_path;
        }

        public void setImage_path(String image_path) {
            this.image_path = image_path;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
