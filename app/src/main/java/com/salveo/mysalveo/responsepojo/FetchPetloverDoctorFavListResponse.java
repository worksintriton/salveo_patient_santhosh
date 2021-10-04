package com.salveo.mysalveo.responsepojo;

import java.util.List;

public class FetchPetloverDoctorFavListResponse {


    /**
     * Status : Success
     * Message : Doctor Fav
     * Data : [{"_id":"603e2a7b2c2b43125f8cb805","doctor_name":"DineshKumar Deva","doctor_img":"http://54.212.108.156:3000/api/uploads/1614688613605.jpeg","specialization":[{"specialization":"Surgeon"},{"specialization":"Internal Medicine Physician"},{"specialization":"Psychiatrist"},{"specialization":"Testing Spef"},{"specialization":"Dermatologist"}],"distance":0,"clinic_name":"Apollo pharmacy","fav":true,"star_count":5,"review_count":22}]
     * Code : 200
     */

    private String Status;
    private String Message;
    private int Code;
    /**
     * _id : 603e2a7b2c2b43125f8cb805
     * doctor_name : DineshKumar Deva
     * doctor_img : http://54.212.108.156:3000/api/uploads/1614688613605.jpeg
     * specialization : [{"specialization":"Surgeon"},{"specialization":"Internal Medicine Physician"},{"specialization":"Psychiatrist"},{"specialization":"Testing Spef"},{"specialization":"Dermatologist"}]
     * distance : 0
     * clinic_name : Apollo pharmacy
     * fav : true
     * star_count : 5
     * review_count : 22
     */

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
        private String _id;
        private String doctor_name;
        private String doctor_img;
        private int distance;
        private String clinic_name;
        private boolean fav;
        private int star_count;
        private int review_count;
        /**
         * specialization : Surgeon
         */

        private String thumbnail_image;
        public String getThumbnail_image() {
            return thumbnail_image;
        }

        public void setThumbnail_image(String thumbnail_image) {
            this.thumbnail_image = thumbnail_image;
        }

        private List<SpecializationBean> specialization;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getDoctor_name() {
            return doctor_name;
        }

        public void setDoctor_name(String doctor_name) {
            this.doctor_name = doctor_name;
        }

        public String getDoctor_img() {
            return doctor_img;
        }

        public void setDoctor_img(String doctor_img) {
            this.doctor_img = doctor_img;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public String getClinic_name() {
            return clinic_name;
        }

        public void setClinic_name(String clinic_name) {
            this.clinic_name = clinic_name;
        }

        public boolean isFav() {
            return fav;
        }

        public void setFav(boolean fav) {
            this.fav = fav;
        }

        public int getStar_count() {
            return star_count;
        }

        public void setStar_count(int star_count) {
            this.star_count = star_count;
        }

        public int getReview_count() {
            return review_count;
        }

        public void setReview_count(int review_count) {
            this.review_count = review_count;
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
}
