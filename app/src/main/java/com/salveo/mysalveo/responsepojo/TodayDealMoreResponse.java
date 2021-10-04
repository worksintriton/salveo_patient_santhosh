package com.salveo.mysalveo.responsepojo;

import java.util.List;

public class TodayDealMoreResponse {

    /**
     * Status : Success
     * Message : todays product list
     * Data : [{"_id":"60ae2c0c48ffef65a41bc546","product_img":"http://54.212.108.156:3000/api/uploads/1625750185578.png","product_title":"Pedigree Vegetarian Adult Dry Food","thumbnail_image":"http://54.212.108.156:3000/api/uploads/1625752843017.png","product_price":108,"product_discount":10,"product_discount_price":"120","product_fav":false,"product_rating":5,"product_review":0},{"_id":"60ae2d1f48ffef65a41bc547","product_img":"http://54.212.108.156:3000/api/uploads/1625748449964.png","product_title":"Orijen Adult Dry Food","thumbnail_image":"http://54.212.108.156:3000/api/uploads/1625752792943.png","product_price":150,"product_discount":0,"product_discount_price":"0","product_fav":false,"product_rating":5,"product_review":0},{"_id":"60e59edd126de24bfbe20fba","product_img":"http://54.212.108.156:3000/api/uploads/1625749127066.png","product_title":"Orijen Large Dry Food","thumbnail_image":"http://54.212.108.156:3000/api/uploads/1625749057500.png","product_price":1350,"product_discount":10,"product_discount_price":"0","product_fav":false,"product_rating":5,"product_review":0},{"_id":"60e5aabd5af36c5c3605bab4","product_img":"http://54.212.108.156:3000/api/uploads/1625748054901.png","product_title":"HUL Natural Shampoo for Puppy","thumbnail_image":"http://54.212.108.156:3000/api/uploads/1625748027413.png","product_price":180,"product_discount":10,"product_discount_price":"0","product_fav":false,"product_rating":5,"product_review":0},{"_id":"60e70af242603a5324f78da1","product_img":"http://54.212.108.156:3000/api/uploads/1625754342692.png","product_title":"Schesir Canned Chicken","thumbnail_image":"http://54.212.108.156:3000/api/uploads/1625754350765.png","product_price":300,"product_discount":0,"product_discount_price":"400","product_fav":false,"product_rating":5,"product_review":0}]
     * product_list_count : 5
     * Code : 200
     */

    private String Status;
    private String Message;
    private int product_list_count;
    private int Code;
    /**
     * _id : 60ae2c0c48ffef65a41bc546
     * product_img : http://54.212.108.156:3000/api/uploads/1625750185578.png
     * product_title : Pedigree Vegetarian Adult Dry Food
     * thumbnail_image : http://54.212.108.156:3000/api/uploads/1625752843017.png
     * product_price : 108
     * product_discount : 10
     * product_discount_price : 120
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
        private String thumbnail_image;
        private int product_price;
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

        public String getThumbnail_image() {
            return thumbnail_image;
        }

        public void setThumbnail_image(String thumbnail_image) {
            this.thumbnail_image = thumbnail_image;
        }

        public int getProduct_price() {
            return product_price;
        }

        public void setProduct_price(int product_price) {
            this.product_price = product_price;
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
