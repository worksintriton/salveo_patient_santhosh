package com.salveo.mysalveo.responsepojo;

import java.util.List;

public class ServiceProviderRegisterFormCreateResponse {

    /**
     * Status : Success
     * Message : SP Added successfully
     * Data : {"bus_service_list":[{"bus_service_list":"Service - 1"},{"bus_service_list":"Service - 2"},{"bus_service_list":"Service - 3"}],"bus_spec_list":[{"bus_spec_list":"Spec -1"},{"bus_spec_list":"Spec -1"},{"bus_spec_list":"Spec -1"}],"bus_service_gall":[{"bus_service_gall":""},{"bus_service_gall":""},{"bus_service_gall":""}],"bus_certif":[{"bus_certif":""},{"bus_certif":""},{"bus_certif":""}],"_id":"5fdb339c2f63d344a1b28672","user_id":"5fc61b82b750da703e48da78","bus_user_name":"mohammed Imthiyas","bus_user_email":"mohammed@gmail.com","bussiness_name":"Mohammed imthiyas","bus_user_phone":"9876543210","bus_profile":"","bus_proof":"","date_and_time":"23-10-2020 12:00 AM","mobile_type":"Admin","delete_status":false,"__v":0}
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
         * bus_service_list : [{"bus_service_list":"Service - 1"},{"bus_service_list":"Service - 2"},{"bus_service_list":"Service - 3"}]
         * bus_spec_list : [{"bus_spec_list":"Spec -1"},{"bus_spec_list":"Spec -1"},{"bus_spec_list":"Spec -1"}]
         * bus_service_gall : [{"bus_service_gall":"http://mysalveo.com/api/uploads/images.jpeg"},{"bus_service_gall":"http://mysalveo.com/api/uploads/images.jpeg"},{"bus_service_gall":"http://mysalveo.com/api/uploads/images.jpeg"}]
         * bus_certif : [{"bus_certif":"http://mysalveo.com/api/uploads/images.jpeg"},{"bus_certif":"http://mysalveo.com/api/uploads/images.jpeg"},{"bus_certif":"http://mysalveo.com/api/uploads/images.jpeg"}]
         * _id : 5fdb339c2f63d344a1b28672
         * user_id : 5fc61b82b750da703e48da78
         * bus_user_name : mohammed Imthiyas
         * bus_user_email : mohammed@gmail.com
         * bussiness_name : Mohammed imthiyas
         * bus_user_phone : 9876543210
         * bus_profile : http://mysalveo.com/api/uploads/images.jpeg
         * bus_proof : http://mysalveo.com/api/uploads/images.jpeg
         * date_and_time : 23-10-2020 12:00 AM
         * mobile_type : Admin
         * delete_status : false
         * __v : 0
         */

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
        private boolean delete_status;
        private int __v;
        private List<BusServiceListBean> bus_service_list;
        private List<BusSpecListBean> bus_spec_list;
        private List<BusServiceGallBean> bus_service_gall;
        private List<BusCertifBean> bus_certif;

        private String sp_loc;
        private double sp_lat;
        private double sp_long;

        private String city_name;
        private String sp_info ;

        public String getSp_info() {
            return sp_info;
        }

        public void setSp_info(String sp_info) {
            this.sp_info = sp_info;
        }

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
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

        public boolean isDelete_status() {
            return delete_status;
        }

        public void setDelete_status(boolean delete_status) {
            this.delete_status = delete_status;
        }

        public int get__v() {
            return __v;
        }

        public void set__v(int __v) {
            this.__v = __v;
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

        public static class BusServiceListBean  {
            /**
             * amount
             * bus_service_list : Service - 1
             * time_slots : 15 mins
             */

            private int amount;

            public int getAmount() {
                return amount;
            }

            public void setAmount(int amount) {
                this.amount = amount;
            }

            private String bus_service_list;
            private String time_slots;


            public String getBus_service_list() {
                return bus_service_list;
            }

            public void setBus_service_list(String bus_service_list) {
                this.bus_service_list = bus_service_list;

            }


            public String getTime_slots() {
                return time_slots;
            }

            public void setTime_slots(String time_slots) {
                this.time_slots = time_slots;

            }
        }

        public static class BusSpecListBean  {
            /**
             * bus_spec_list : Spec -1
             */

            private String bus_spec_list;

            public String getBus_spec_list() {
                return bus_spec_list;
            }

            public void setBus_spec_list(String bus_spec_list) {
                this.bus_spec_list = bus_spec_list;
            }
        }

        public static class BusServiceGallBean  {
            /**
             * bus_service_gall : http://mysalveo.com/api/uploads/images.jpeg
             */

            private String bus_service_gall;

            public String getBus_service_gall() {
                return bus_service_gall;
            }

            public void setBus_service_gall(String bus_service_gall) {
                this.bus_service_gall = bus_service_gall;
            }
        }

        public static class BusCertifBean {
            /**
             * bus_certif : http://mysalveo.com/api/uploads/images.jpeg
             */

            private String bus_certif;

            public String getBus_certif() {
                return bus_certif;
            }

            public void setBus_certif(String bus_certif) {
                this.bus_certif = bus_certif;
            }
        }
    }
}
