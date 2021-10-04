package com.salveo.mysalveo.requestpojo;

import java.util.List;

public class ServiceProviderRegisterFormCreateRequest {


    /**
     * user_id : 5fc61b82b750da703e48da78
     * bus_user_name : mohammed Imthiyas
     * profile_status : true
     * profile_verification_status : Not verified
     * sp_loc : No, chennai tamil nadu
     * sp_lat : 12.00909
     * sp_long : 80.980098
     * bus_user_email : mohammed@gmail.com
     * bussiness_name : Mohammed imthiyas
     * bus_user_phone : 9876543210
     * bus_service_list : [{"bus_service_list":"Service - 1","time_slots":"15 mins"},{"bus_service_list":"Service - 2","time_slots":"15 mins"},{"bus_service_list":"Service - 3","time_slots":"15 mins"}]
     * bus_spec_list : [{"bus_spec_list":"Spec -1"},{"bus_spec_list":"Spec -1"},{"bus_spec_list":"Spec -1"}]
     * bus_service_gall : [{"bus_service_gall":""},{"bus_service_gall":""},{"bus_service_gall":""}]
     * bus_profile :
     * bus_proof :
     * bus_certif : [{"bus_certif":""},{"bus_certif":""},{"bus_certif":""}]
     * date_and_time : 23-10-2020 12:00 AM
     * mobile_type : Admin
     * city_name : Chennai
     */

    private String user_id;
    private String _id;
    private String bus_user_name;
    private String bus_user_email;
    private String bussiness_name;
    private String bus_user_phone;
    private String bus_profile;
    private String bus_proof;
    private String date_and_time;
    private String mobile_type;
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

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    private boolean profile_status;
    private String profile_verification_status;
    private String sp_loc;
    private double sp_lat;
    private double sp_long;

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

    private List<BusServiceListBean> bus_service_list;
    private List<BusSpecListBean> bus_spec_list;
    private List<BusServiceGallBean> bus_service_gall;
    private List<BusCertifBean> bus_certif;


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
         * bus_service_list : Service - 1
         * time_slots : 15 mins
         * amount : 0
         */

        private String bus_service_list;
        private String time_slots;
        private int amount;


        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

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
         * bus_service_gall :
         */

        private String bus_service_gall;

        public String getBus_service_gall() {
            return bus_service_gall;
        }

        public void setBus_service_gall(String bus_service_gall) {
            this.bus_service_gall = bus_service_gall;
        }
    }

    public static class BusCertifBean  {
        /**
         * bus_certif :
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
