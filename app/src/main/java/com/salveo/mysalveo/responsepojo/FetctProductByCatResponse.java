package com.salveo.mysalveo.responsepojo;

import java.util.List;

public class FetctProductByCatResponse {

    /**
     * Status : Success
     * Message : product list
     * Data : [{"_id":"60e5aabd5af36c5c3605bab4","product_img":"http://54.212.108.156:3000/api/uploads/1625748054901.png","product_title":"HUL Natural Shampoo for Puppy","product_price":180,"thumbnail_image":"http://54.212.108.156:3000/api/uploads/1625748027413.png","product_discount":10,"product_discount_price":0,"product_fav":false,"product_rating":5,"product_review":0},{"_id":"60e5aad15af36c5c3605bab5","product_img":"http://54.212.108.156:3000/api/uploads/1625747945821.png","product_title":"Organic Anti-Tick and Flea Spray for Dogs","product_price":500,"thumbnail_image":"http://54.212.108.156:3000/api/uploads/1625747987678.png","product_discount":0,"product_discount_price":0,"product_fav":false,"product_rating":5,"product_review":0}]
     * product_list_count : 2
     * Code : 200
     */

    private String Status;
    private String Message;
    private int product_list_count;
    private int Code;
    /**
     * _id : 60e5aabd5af36c5c3605bab4
     * product_img : http://54.212.108.156:3000/api/uploads/1625748054901.png
     * product_title : HUL Natural Shampoo for Puppy
     * product_price : 180
     * thumbnail_image : http://54.212.108.156:3000/api/uploads/1625748027413.png
     * product_discount : 10
     * product_discount_price : 0
     * product_fav : false
     * product_rating : 5
     * product_review : 0
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

    public int getProduct_list_count() {
        return product_list_count;
    }

    public void setProduct_list_count(int product_list_count) {
        this.product_list_count = product_list_count;
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
        private String product_img;
        private String product_title;
        private int product_price;
        private String thumbnail_image;
        private int product_discount;
        private int product_discount_price;
        private boolean product_fav;
        private int product_rating;
        private int product_review;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getProduct_img() {
            return product_img;
        }

        public void setProduct_img(String product_img) {
            this.product_img = product_img;
        }

        public String getProduct_title() {
            return product_title;
        }

        public void setProduct_title(String product_title) {
            this.product_title = product_title;
        }

        public int getProduct_price() {
            return product_price;
        }

        public void setProduct_price(int product_price) {
            this.product_price = product_price;
        }

        public String getThumbnail_image() {
            return thumbnail_image;
        }

        public void setThumbnail_image(String thumbnail_image) {
            this.thumbnail_image = thumbnail_image;
        }

        public int getProduct_discount() {
            return product_discount;
        }

        public void setProduct_discount(int product_discount) {
            this.product_discount = product_discount;
        }

        public int getProduct_discount_price() {
            return product_discount_price;
        }

        public void setProduct_discount_price(int product_discount_price) {
            this.product_discount_price = product_discount_price;
        }

        public boolean isProduct_fav() {
            return product_fav;
        }

        public void setProduct_fav(boolean product_fav) {
            this.product_fav = product_fav;
        }

        public int getProduct_rating() {
            return product_rating;
        }

        public void setProduct_rating(int product_rating) {
            this.product_rating = product_rating;
        }

        public int getProduct_review() {
            return product_review;
        }

        public void setProduct_review(int product_review) {
            this.product_review = product_review;
        }
    }
}
