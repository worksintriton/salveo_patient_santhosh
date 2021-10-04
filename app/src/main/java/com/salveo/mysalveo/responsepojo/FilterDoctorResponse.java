package com.salveo.mysalveo.responsepojo;

import java.util.List;

public class FilterDoctorResponse {

    /**
     * Status : Success
     * Message : Filtered Doctor List
     * Data : [{"_id":"60acb1bb68492a4567b3f509","user_id":"60acb15868492a4567b3f508","dr_title":"Dr","doctor_name":"Sri Dinesh","clinic_name":"Chennai Pet Clinic","specialization":[{"specialization":"General Consultant"},{"specialization":"Pathology"},{"specialization":"Theriogenology"}],"city_name":"","doctor_img":"http://54.212.108.156:3000/api/uploads/60acb15868492a4567b3f5082505134303","clinic_loc":"7, Rajiv Gandhi Nagar Rd, Nanmangalam, Chennai, Tamil Nadu 600117, India","communication_type":"Online Or Visit","distance":"267.34","star_count":2.5,"review_count":234,"amount":1},{"_id":"60be0018fcc6541fe0b227aa","user_id":"60bdff94fcc6541fe0b227a9","dr_title":"Dr","doctor_name":"DINESH KUMAR","clinic_name":"Apollo hospital","specialization":[{"specialization":"General Consultant"},{"specialization":"Virology"},{"specialization":"Nutrition"},{"specialization":"Microbiology"},{"specialization":"Surgery"}],"city_name":"","doctor_img":"http://54.212.108.156:3000/api/uploads/1623064543096.jpeg","clinic_loc":"Iluppaiyur Karatampatti Iluppaiyur road, Iluppaiyur, Tamil Nadu 621006, India","communication_type":"Online Or Visit","distance":"0.00","star_count":2.5,"review_count":234,"amount":3500},{"_id":"60e4077977095a641e4284c5","user_id":"60acb15868492a4567b3f508","dr_title":"Dr","doctor_name":"Sri Dinesh jack","clinic_name":"Trichy pet clinic","specialization":[{"specialization":"General Consultant"},{"specialization":"Virology"},{"specialization":"Pathology"},{"specialization":"Laboratory Animal Medicine"},{"specialization":"Internal Medicine"},{"specialization":"Equine Medicine"}],"city_name":"","doctor_img":"http://54.212.108.156:3000/api/uploads/1625556809177.jpeg","clinic_loc":"Iluppaiyur Karatampatti Iluppaiyur road, Iluppaiyur, Tamil Nadu 621006, India","communication_type":"Online Or Visit","distance":"0.01","star_count":2.5,"review_count":234,"amount":500},{"_id":"60e5840ac222f93706c11e26","user_id":"60e58359d6f9cc31c836153d","dr_title":"Dr","doctor_name":"Maddy Krish","clinic_name":"Maddy Clinic Pet","specialization":[{"specialization":"General Consultant"},{"specialization":"Virology"}],"city_name":"Salem","doctor_img":"http://54.212.108.156:3000/api/uploads/1625654237269.jpg","clinic_loc":"Salem 133, Vmr Theatre Back Side Rd, Shevapet, Salem, Tamil Nadu 636002, India","communication_type":"Online","distance":"85.68","star_count":2.5,"review_count":234,"amount":200}]
     * banner : [{"title":"title1","image_path":"http://54.212.108.156:3000/api/uploads/60b4dc30541a437c7b9c60f43105182553"},{"title":"title1","image_path":"http://54.212.108.156:3000/api/uploads/60b4dc30541a437c7b9c60f43105182553"},{"title":"title1","image_path":"http://54.212.108.156:3000/api/uploads/60b4dc30541a437c7b9c60f43105182553"},{"title":"title1","image_path":"http://54.212.108.156:3000/api/uploads/60b4dc30541a437c7b9c60f43105182553"}]
     * Code : 200
     */

    private String Status;
    private String Message;
    private int Code;
    /**
     * _id : 60acb1bb68492a4567b3f509
     * user_id : 60acb15868492a4567b3f508
     * dr_title : Dr
     * doctor_name : Sri Dinesh
     * clinic_name : Chennai Pet Clinic
     * specialization : [{"specialization":"General Consultant"},{"specialization":"Pathology"},{"specialization":"Theriogenology"}]
     * city_name :
     * doctor_img : http://54.212.108.156:3000/api/uploads/60acb15868492a4567b3f5082505134303
     * clinic_loc : 7, Rajiv Gandhi Nagar Rd, Nanmangalam, Chennai, Tamil Nadu 600117, India
     * communication_type : Online Or Visit
     * distance : 267.34
     * star_count : 2.5
     * review_count : 234
     * amount : 1
     */

    private List<DataBean> Data;
    /**
     * title : title1
     * image_path : http://54.212.108.156:3000/api/uploads/60b4dc30541a437c7b9c60f43105182553
     */

    private List<BannerBean> banner;

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

    public List<BannerBean> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerBean> banner) {
        this.banner = banner;
    }

    public static class DataBean {
        private String _id;
        private String user_id;
        private String dr_title;
        private String doctor_name;
        private String clinic_name;
        private String city_name;
        private String doctor_img;
        private String clinic_loc;
        private String communication_type;
        private String distance;
        private double star_count;
        private int review_count;
        private int amount;
        /**
         * specialization : General Consultant
         */

        private List<SpecializationBean> specialization;

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

        public String getDr_title() {
            return dr_title;
        }

        public void setDr_title(String dr_title) {
            this.dr_title = dr_title;
        }

        public String getDoctor_name() {
            return doctor_name;
        }

        public void setDoctor_name(String doctor_name) {
            this.doctor_name = doctor_name;
        }

        public String getClinic_name() {
            return clinic_name;
        }

        public void setClinic_name(String clinic_name) {
            this.clinic_name = clinic_name;
        }

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

        public String getDoctor_img() {
            return doctor_img;
        }

        public void setDoctor_img(String doctor_img) {
            this.doctor_img = doctor_img;
        }

        public String getClinic_loc() {
            return clinic_loc;
        }

        public void setClinic_loc(String clinic_loc) {
            this.clinic_loc = clinic_loc;
        }

        public String getCommunication_type() {
            return communication_type;
        }

        public void setCommunication_type(String communication_type) {
            this.communication_type = communication_type;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public double getStar_count() {
            return star_count;
        }

        public void setStar_count(double star_count) {
            this.star_count = star_count;
        }

        public int getReview_count() {
            return review_count;
        }

        public void setReview_count(int review_count) {
            this.review_count = review_count;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public List<SpecializationBean> getSpecialization() {
            return specialization;
        }

        public void setSpecialization(List<SpecializationBean> specialization) {
            this.specialization = specialization;
        }

        public static class SpecializationBean {
            private String specialization;

            public String getSpecialization() {
                return specialization;
            }

            public void setSpecialization(String specialization) {
                this.specialization = specialization;
            }
        }
    }

    public static class BannerBean {
        private String title;
        private String image_path;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImage_path() {
            return image_path;
        }

        public void setImage_path(String image_path) {
            this.image_path = image_path;
        }
    }
}
