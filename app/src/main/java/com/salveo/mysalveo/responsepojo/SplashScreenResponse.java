package com.salveo.mysalveo.responsepojo;

import java.util.List;

public class SplashScreenResponse {


    /**
     * Status : Success
     * Message : Splash screen  Details
     * Data : [{"_id":"602e1fbcdbc27515ce476848","img_path":"http://54.212.108.156:3000/api/uploads/New Project (1).jpg","img_title":"My Mobile","img_index":1,"show_status":true,"date_and_time":"23-10-2020 12:10 AM","delete_status":false,"updatedAt":"2021-02-18T08:05:16.170Z","createdAt":"2021-02-18T08:05:16.170Z","__v":0},{"_id":"602e1fc0dbc27515ce476849","img_path":"http://54.212.108.156:3000/api/uploads/New Project (1).jpg","img_title":"My Mobile","img_index":2,"show_status":true,"date_and_time":"23-10-2020 12:10 AM","delete_status":false,"updatedAt":"2021-02-18T08:05:20.481Z","createdAt":"2021-02-18T08:05:20.481Z","__v":0},{"_id":"602e1fc4dbc27515ce47684a","img_path":"http://54.212.108.156:3000/api/uploads/New Project (1).jpg","img_title":"My Mobile","img_index":3,"show_status":true,"date_and_time":"23-10-2020 12:10 AM","delete_status":false,"updatedAt":"2021-02-18T08:05:24.772Z","createdAt":"2021-02-18T08:05:24.772Z","__v":0}]
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
         * _id : 602e1fbcdbc27515ce476848
         * img_path : http://54.212.108.156:3000/api/uploads/New Project (1).jpg
         * img_title : My Mobile
         * img_index : 1
         * show_status : true
         * date_and_time : 23-10-2020 12:10 AM
         * delete_status : false
         * updatedAt : 2021-02-18T08:05:16.170Z
         * createdAt : 2021-02-18T08:05:16.170Z
         * __v : 0
         */

        private String _id;
        private String img_path;
        private String img_title;
        private int img_index;
        private boolean show_status;
        private String date_and_time;
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


        public String getImg_path() {
            return img_path;
        }

        public void setImg_path(String img_path) {
            this.img_path = img_path;

        }


        public String getImg_title() {
            return img_title;
        }

        public void setImg_title(String img_title) {
            this.img_title = img_title;

        }


        public int getImg_index() {
            return img_index;
        }

        public void setImg_index(int img_index) {
            this.img_index = img_index;

        }


        public boolean isShow_status() {
            return show_status;
        }

        public void setShow_status(boolean show_status) {
            this.show_status = show_status;

        }


        public String getDate_and_time() {
            return date_and_time;
        }

        public void setDate_and_time(String date_and_time) {
            this.date_and_time = date_and_time;

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
