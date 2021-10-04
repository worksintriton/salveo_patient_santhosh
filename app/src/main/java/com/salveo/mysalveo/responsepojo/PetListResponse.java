package com.salveo.mysalveo.responsepojo;

import java.io.Serializable;
import java.util.List;

public class PetListResponse {

    /**
     * Status : Success
     * Message : pet details List
     * Data : [{"pet_img":[{"pet_img":"http://54.212.108.156:3000/api/uploads/1614686158761.jpeg"}],"_id":"603e27a92c2b43125f8cb803","user_id":"603e27792c2b43125f8cb802","pet_name":"DEENU","pet_type":"Dog","pet_breed":"Irish Setter","pet_gender":"Male","pet_color":"BLACK","pet_weight":20.5,"pet_age":"4 Years 1 Months","pet_dob":"02-03-2016","pet_spayed":true,"pet_purebred":false,"pet_frnd_with_dog":false,"pet_frnd_with_cat":true,"pet_frnd_with_kit":false,"pet_microchipped":false,"pet_tick_free":false,"pet_private_part":false,"vaccinated":true,"last_vaccination_date":"10-02-2021","default_status":true,"date_and_time":"02-03-2021 05:25 PM","mobile_type":"Android","delete_status":false,"updatedAt":"2021-03-02T11:56:02.149Z","createdAt":"2021-03-02T11:55:21.396Z","__v":0}]
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

    public static class DataBean  {
        /**
         * pet_img : [{"pet_img":"http://54.212.108.156:3000/api/uploads/1614686158761.jpeg"}]
         * _id : 603e27a92c2b43125f8cb803
         * user_id : 603e27792c2b43125f8cb802
         * pet_name : DEENU
         * pet_type : Dog
         * pet_breed : Irish Setter
         * pet_gender : Male
         * pet_color : BLACK
         * pet_weight : 20.5
         * pet_age : 4 Years 1 Months
         * pet_dob : 02-03-2016
         * pet_spayed : true
         * pet_purebred : false
         * pet_frnd_with_dog : false
         * pet_frnd_with_cat : true
         * pet_frnd_with_kit : false
         * pet_microchipped : false
         * pet_tick_free : false
         * pet_private_part : false
         * vaccinated : true
         * last_vaccination_date : 10-02-2021
         * default_status : true
         * date_and_time : 02-03-2021 05:25 PM
         * mobile_type : Android
         * delete_status : false
         * updatedAt : 2021-03-02T11:56:02.149Z
         * createdAt : 2021-03-02T11:55:21.396Z
         * __v : 0
         */

        private String _id;
        private String user_id;
        private String pet_name;
        private String pet_type;
        private String pet_breed;
        private String pet_gender;
        private String pet_color;
        private double pet_weight;
        private String pet_age;
        private String pet_dob;
        private boolean pet_spayed;
        private boolean pet_purebred;
        private boolean pet_frnd_with_dog;
        private boolean pet_frnd_with_cat;
        private boolean pet_frnd_with_kit;
        private boolean pet_microchipped;
        private boolean pet_tick_free;
        private boolean pet_private_part;
        private boolean vaccinated;
        private String last_vaccination_date;
        private boolean default_status;
        private String date_and_time;
        private String mobile_type;
        private boolean delete_status;
        private String updatedAt;
        private String createdAt;
        private int __v;
        private List<PetImgBean> pet_img;


        private String petbio;

        public String getPetbio() {
            return petbio;
        }

        public void setPetbio(String petbio) {
            this.petbio = petbio;
        }

        private boolean isSelected ;
        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
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


        public String getPet_name() {
            return pet_name;
        }

        public void setPet_name(String pet_name) {
            this.pet_name = pet_name;

        }


        public String getPet_type() {
            return pet_type;
        }

        public void setPet_type(String pet_type) {
            this.pet_type = pet_type;

        }


        public String getPet_breed() {
            return pet_breed;
        }

        public void setPet_breed(String pet_breed) {
            this.pet_breed = pet_breed;
        }


        public String getPet_gender() {
            return pet_gender;
        }

        public void setPet_gender(String pet_gender) {
            this.pet_gender = pet_gender;

        }


        public String getPet_color() {
            return pet_color;
        }

        public void setPet_color(String pet_color) {
            this.pet_color = pet_color;

        }


        public double getPet_weight() {
            return pet_weight;
        }

        public void setPet_weight(double pet_weight) {
            this.pet_weight = pet_weight;

        }

        public String getPet_age() {
            return pet_age;
        }

        public void setPet_age(String pet_age) {
            this.pet_age = pet_age;
        }


        public String getPet_dob() {
            return pet_dob;
        }

        public void setPet_dob(String pet_dob) {
            this.pet_dob = pet_dob;

        }


        public boolean isPet_spayed() {
            return pet_spayed;
        }

        public void setPet_spayed(boolean pet_spayed) {
            this.pet_spayed = pet_spayed;

        }


        public boolean isPet_purebred() {
            return pet_purebred;
        }

        public void setPet_purebred(boolean pet_purebred) {
            this.pet_purebred = pet_purebred;

        }


        public boolean isPet_frnd_with_dog() {
            return pet_frnd_with_dog;
        }

        public void setPet_frnd_with_dog(boolean pet_frnd_with_dog) {
            this.pet_frnd_with_dog = pet_frnd_with_dog;

        }


        public boolean isPet_frnd_with_cat() {
            return pet_frnd_with_cat;
        }

        public void setPet_frnd_with_cat(boolean pet_frnd_with_cat) {
            this.pet_frnd_with_cat = pet_frnd_with_cat;

        }


        public boolean isPet_frnd_with_kit() {
            return pet_frnd_with_kit;
        }

        public void setPet_frnd_with_kit(boolean pet_frnd_with_kit) {
            this.pet_frnd_with_kit = pet_frnd_with_kit;

        }


        public boolean isPet_microchipped() {
            return pet_microchipped;
        }

        public void setPet_microchipped(boolean pet_microchipped) {
            this.pet_microchipped = pet_microchipped;

        }


        public boolean isPet_tick_free() {
            return pet_tick_free;
        }

        public void setPet_tick_free(boolean pet_tick_free) {
            this.pet_tick_free = pet_tick_free;

        }


        public boolean isPet_private_part() {
            return pet_private_part;
        }

        public void setPet_private_part(boolean pet_private_part) {
            this.pet_private_part = pet_private_part;

        }


        public boolean isVaccinated() {
            return vaccinated;
        }

        public void setVaccinated(boolean vaccinated) {
            this.vaccinated = vaccinated;

        }


        public String getLast_vaccination_date() {
            return last_vaccination_date;
        }

        public void setLast_vaccination_date(String last_vaccination_date) {
            this.last_vaccination_date = last_vaccination_date;

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


        public List<PetImgBean> getPet_img() {
            return pet_img;
        }

        public void setPet_img(List<PetImgBean> pet_img) {
            this.pet_img = pet_img;

        }

        public static class PetImgBean implements Serializable{
            /**
             * pet_img : http://54.212.108.156:3000/api/uploads/1614686158761.jpeg
             */

            private String pet_img;


            public String getPet_img() {
                return pet_img;
            }

            public void setPet_img(String pet_img) {
                this.pet_img = pet_img;

            }


        }
    }
}
