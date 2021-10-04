package com.salveo.mysalveo.responsepojo;

import java.util.List;

public class BreedTypeResponse {

    /**
     * Status : Success
     * Message : breed type List
     * Data : [{"_id":"5fbf61fa2af5cc11f61a1bf6","pet_type_id":"5fb8b160e0ba3b0e6268dd2e","pet_breed":"Testing - 4","date_and_time":"11/26/2020, 1:36:18 PM","__v":0}]
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
         * _id : 5fbf61fa2af5cc11f61a1bf6
         * pet_type_id : 5fb8b160e0ba3b0e6268dd2e
         * pet_breed : Testing - 4
         * date_and_time : 11/26/2020, 1:36:18 PM
         * __v : 0
         */

        private String _id;
        private String pet_type_id;
        private String pet_breed;
        private String date_and_time;
        private String pet_breed_img;

        private boolean isSelected ;
        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        public String getPet_breed_img() {
            return pet_breed_img;
        }

        public void setPet_breed_img(String pet_breed_img) {
            this.pet_breed_img = pet_breed_img;
        }

        private int __v;


        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;

        }


        public String getPet_type_id() {
            return pet_type_id;
        }

        public void setPet_type_id(String pet_type_id) {
            this.pet_type_id = pet_type_id;

        }

        public String getPet_breed() {
            return pet_breed;
        }

        public void setPet_breed(String pet_breed) {
            this.pet_breed = pet_breed;

        }


        public String getDate_and_time() {
            return date_and_time;
        }

        public void setDate_and_time(String date_and_time) {
            this.date_and_time = date_and_time;

        }


        public int get__v() {
            return __v;
        }

        public void set__v(int __v) {
            this.__v = __v;

        }
    }
}
