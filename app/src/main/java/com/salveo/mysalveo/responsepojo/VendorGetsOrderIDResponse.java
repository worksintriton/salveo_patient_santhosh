package com.salveo.mysalveo.responsepojo;

import java.util.List;

public class VendorGetsOrderIDResponse {


    /**
     * Status : Success
     * Message : Vendor List
     * Data : {"bussiness_gallery":[{"bussiness_gallery":"http://54.212.108.156:3000/api/uploads/1615357588430.png"}],"certifi":[{"certifi":"http://54.212.108.156:3000/api/uploads/1615357601709.pdf"}],"_id":"604866a50b3a487571a1c568","user_id":"6048589d0b3a487571a1c567","user_name":"Sam","user_email":"santhoshnrv94@gmail.com","bussiness_name":"Sam Vendor","bussiness_email":"santhoshnrv94@gmail.com","bussiness":"Pet Food","bussiness_phone":"8081828384","business_reg":"GST","photo_id_proof":"http://54.212.108.156:3000/api/uploads/1615357593323.pdf","govt_id_proof":"http://54.212.108.156:3000/api/uploads/1615357597537.pdf","date_and_time":"10/03/2021 11:56 AM","mobile_type":"Android","profile_status":true,"profile_verification_status":"Verified","bussiness_loc":"Mountain View 1600 Amphitheatre Pkwy, Mountain View, CA 94043, USA","bussiness_lat":37.421998333333335,"bussiness_long":-122.08400000000002,"delete_status":false,"updatedAt":"2021-03-10T06:27:40.929Z","createdAt":"2021-03-10T06:26:45.479Z","__v":0}
     * Code : 200
     */

    private String Status;
    private String Message;
    /**
     * bussiness_gallery : [{"bussiness_gallery":"http://54.212.108.156:3000/api/uploads/1615357588430.png"}]
     * certifi : [{"certifi":"http://54.212.108.156:3000/api/uploads/1615357601709.pdf"}]
     * _id : 604866a50b3a487571a1c568
     * user_id : 6048589d0b3a487571a1c567
     * user_name : Sam
     * user_email : santhoshnrv94@gmail.com
     * bussiness_name : Sam Vendor
     * bussiness_email : santhoshnrv94@gmail.com
     * bussiness : Pet Food
     * bussiness_phone : 8081828384
     * business_reg : GST
     * photo_id_proof : http://54.212.108.156:3000/api/uploads/1615357593323.pdf
     * govt_id_proof : http://54.212.108.156:3000/api/uploads/1615357597537.pdf
     * date_and_time : 10/03/2021 11:56 AM
     * mobile_type : Android
     * profile_status : true
     * profile_verification_status : Verified
     * bussiness_loc : Mountain View 1600 Amphitheatre Pkwy, Mountain View, CA 94043, USA
     * bussiness_lat : 37.421998333333335
     * bussiness_long : -122.08400000000002
     * delete_status : false
     * updatedAt : 2021-03-10T06:27:40.929Z
     * createdAt : 2021-03-10T06:26:45.479Z
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
        private String user_id;
        private String user_name;
        private String user_email;
        private String bussiness_name;
        private String bussiness_email;
        private String bussiness;
        private String bussiness_phone;
        private String business_reg;
        private String photo_id_proof;
        private String govt_id_proof;
        private String date_and_time;
        private String mobile_type;
        private boolean profile_status;
        private String profile_verification_status;
        private String bussiness_loc;
        private double bussiness_lat;
        private double bussiness_long;
        private boolean delete_status;
        private String updatedAt;
        private String createdAt;
        private int __v;
        /**
         * bussiness_gallery : http://54.212.108.156:3000/api/uploads/1615357588430.png
         */

        private List<BussinessGalleryBean> bussiness_gallery;
        /**
         * certifi : http://54.212.108.156:3000/api/uploads/1615357601709.pdf
         */

        private List<CertifiBean> certifi;

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

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getUser_email() {
            return user_email;
        }

        public void setUser_email(String user_email) {
            this.user_email = user_email;
        }

        public String getBussiness_name() {
            return bussiness_name;
        }

        public void setBussiness_name(String bussiness_name) {
            this.bussiness_name = bussiness_name;
        }

        public String getBussiness_email() {
            return bussiness_email;
        }

        public void setBussiness_email(String bussiness_email) {
            this.bussiness_email = bussiness_email;
        }

        public String getBussiness() {
            return bussiness;
        }

        public void setBussiness(String bussiness) {
            this.bussiness = bussiness;
        }

        public String getBussiness_phone() {
            return bussiness_phone;
        }

        public void setBussiness_phone(String bussiness_phone) {
            this.bussiness_phone = bussiness_phone;
        }

        public String getBusiness_reg() {
            return business_reg;
        }

        public void setBusiness_reg(String business_reg) {
            this.business_reg = business_reg;
        }

        public String getPhoto_id_proof() {
            return photo_id_proof;
        }

        public void setPhoto_id_proof(String photo_id_proof) {
            this.photo_id_proof = photo_id_proof;
        }

        public String getGovt_id_proof() {
            return govt_id_proof;
        }

        public void setGovt_id_proof(String govt_id_proof) {
            this.govt_id_proof = govt_id_proof;
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

        public String getBussiness_loc() {
            return bussiness_loc;
        }

        public void setBussiness_loc(String bussiness_loc) {
            this.bussiness_loc = bussiness_loc;
        }

        public double getBussiness_lat() {
            return bussiness_lat;
        }

        public void setBussiness_lat(double bussiness_lat) {
            this.bussiness_lat = bussiness_lat;
        }

        public double getBussiness_long() {
            return bussiness_long;
        }

        public void setBussiness_long(double bussiness_long) {
            this.bussiness_long = bussiness_long;
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

        public List<BussinessGalleryBean> getBussiness_gallery() {
            return bussiness_gallery;
        }

        public void setBussiness_gallery(List<BussinessGalleryBean> bussiness_gallery) {
            this.bussiness_gallery = bussiness_gallery;
        }

        public List<CertifiBean> getCertifi() {
            return certifi;
        }

        public void setCertifi(List<CertifiBean> certifi) {
            this.certifi = certifi;
        }

        public static class BussinessGalleryBean {
            private String bussiness_gallery;

            public String getBussiness_gallery() {
                return bussiness_gallery;
            }

            public void setBussiness_gallery(String bussiness_gallery) {
                this.bussiness_gallery = bussiness_gallery;
            }
        }

        public static class CertifiBean {
            private String certifi;

            public String getCertifi() {
                return certifi;
            }

            public void setCertifi(String certifi) {
                this.certifi = certifi;
            }
        }
    }
}
