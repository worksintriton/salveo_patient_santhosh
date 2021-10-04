package com.salveo.mysalveo.responsepojo;

import java.util.List;

public class ShopDashboardResponse {

    /**
     * Status : Success
     * Message : product list
     * Data : {"Banner_details":[{"banner_img":"http://54.212.108.156:3000/api/uploads/1625686845460.png","banner_title":"."},{"banner_img":"http://54.212.108.156:3000/api/uploads/1625686840134.png","banner_title":"."},{"banner_img":"http://54.212.108.156:3000/api/uploads/1625686831097.png","banner_title":"."}],"Today_Special":[{"_id":"60ae2d1f48ffef65a41bc547","product_img":"http://54.212.108.156:3000/api/uploads/1625748449964.png","product_title":"Wild Earth Adult Dry Food","thumbnail_image":"http://54.212.108.156:3000/api/uploads/1625748428221.png","product_price":150,"product_discount":0,"product_fav":true,"product_rating":5,"product_review":0},{"_id":"60ae2c0c48ffef65a41bc546","product_img":"http://54.212.108.156:3000/api/uploads/1622027270640.jpeg","product_title":"Pedigree Vegetarian Adult Dry Food","thumbnail_image":"http://54.212.108.156:3000/api/uploads/Pic_empty.jpg","product_price":108,"product_discount":10,"product_fav":true,"product_rating":5,"product_review":0}],"Product_details":[{"cat_id":"5fec1573ea832e2e73c1fc7a","cat_name":"Bedding","product_list":[{"_id":"60e5adc55af36c5c3605bab6","product_img":"http://54.212.108.156:3000/api/uploads/1625747585002.png","product_title":"Nap Now Lounger Dog Bed - Black","thumbnail_image":"http://54.212.108.156:3000/api/uploads/1625747535197.png","product_price":100,"product_discount":0,"product_fav":false,"product_rating":5,"product_review":0}]},{"cat_id":"5fec14a5ea832e2e73c1fc79","cat_name":"Pet Foods - Wet","product_list":[{"_id":"60b0c4ae67f25056fe286ca2","product_img":"http://54.212.108.156:3000/api/uploads/1625748318970.png","product_title":"Royal Canin Maxi Adult Dog Wet Food","thumbnail_image":"http://54.212.108.156:3000/api/uploads/1625748348042.png","product_price":200,"product_discount":0,"product_fav":false,"product_rating":5,"product_review":0}]},{"cat_id":"5fec1424ea832e2e73c1fc78","cat_name":"Pet Foods - Dry","product_list":[{"_id":"60e59edd126de24bfbe20fba","product_img":"http://54.212.108.156:3000/api/uploads/1622641300034.jpg","product_title":"Dog Food","thumbnail_image":"http://54.212.108.156:3000/api/uploads/1625737748404.jpeg","product_price":1500,"product_discount":0,"product_fav":false,"product_rating":5,"product_review":0},{"_id":"60b88167ce808211002cc7bd","product_img":"http://54.212.108.156:3000/api/uploads/1622704481468.jpg","product_title":"Pedigree Milk & Vegetables Dry Puppy Food","thumbnail_image":"http://54.212.108.156:3000/api/uploads/1625737748404.jpeg","product_price":100,"product_discount":0,"product_fav":false,"product_rating":5,"product_review":0},{"_id":"60ae2d1f48ffef65a41bc547","product_img":"http://54.212.108.156:3000/api/uploads/1625748449964.png","product_title":"Wild Earth Adult Dry Food","thumbnail_image":"http://54.212.108.156:3000/api/uploads/1625748428221.png","product_price":150,"product_discount":0,"product_fav":true,"product_rating":5,"product_review":0},{"_id":"60ae2c0c48ffef65a41bc546","product_img":"http://54.212.108.156:3000/api/uploads/1622027270640.jpeg","product_title":"Pedigree Vegetarian Adult Dry Food","thumbnail_image":"http://54.212.108.156:3000/api/uploads/Pic_empty.jpg","product_price":108,"product_discount":10,"product_fav":true,"product_rating":5,"product_review":0}]}]}
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

    public static class DataBean {
        /**
         * banner_img : http://54.212.108.156:3000/api/uploads/1625686845460.png
         * banner_title : .
         */

        private List<BannerDetailsBean> Banner_details;
        /**
         * _id : 60ae2d1f48ffef65a41bc547
         * product_img : http://54.212.108.156:3000/api/uploads/1625748449964.png
         * product_title : Wild Earth Adult Dry Food
         * thumbnail_image : http://54.212.108.156:3000/api/uploads/1625748428221.png
         * product_price : 150
         * product_discount : 0
         * product_fav : true
         * product_rating : 5
         * product_review : 0
         */

        private List<TodaySpecialBean> Today_Special;
        /**
         * cat_id : 5fec1573ea832e2e73c1fc7a
         * cat_name : Bedding
         * product_list : [{"_id":"60e5adc55af36c5c3605bab6","product_img":"http://54.212.108.156:3000/api/uploads/1625747585002.png","product_title":"Nap Now Lounger Dog Bed - Black","thumbnail_image":"http://54.212.108.156:3000/api/uploads/1625747535197.png","product_price":100,"product_discount":0,"product_fav":false,"product_rating":5,"product_review":0}]
         */

        private List<ProductDetailsBean> Product_details;

        public List<BannerDetailsBean> getBanner_details() {
            return Banner_details;
        }

        public void setBanner_details(List<BannerDetailsBean> Banner_details) {
            this.Banner_details = Banner_details;
        }

        public List<TodaySpecialBean> getToday_Special() {
            return Today_Special;
        }

        public void setToday_Special(List<TodaySpecialBean> Today_Special) {
            this.Today_Special = Today_Special;
        }

        public List<ProductDetailsBean> getProduct_details() {
            return Product_details;
        }

        public void setProduct_details(List<ProductDetailsBean> Product_details) {
            this.Product_details = Product_details;
        }

        public static class BannerDetailsBean {
            private String banner_img;
            private String banner_title;

            public String getBanner_img() {
                return banner_img;
            }

            public void setBanner_img(String banner_img) {
                this.banner_img = banner_img;
            }

            public String getBanner_title() {
                return banner_title;
            }

            public void setBanner_title(String banner_title) {
                this.banner_title = banner_title;
            }
        }

        public static class TodaySpecialBean {
            private String _id;
            private String product_img;
            private String product_title;
            private String thumbnail_image;
            private int product_price;
            private int product_discount_price;

            public int getProduct_discount_price() {
                return product_discount_price;
            }

            public void setProduct_discount_price(int product_discount_price) {
                this.product_discount_price = product_discount_price;
            }

            private int product_discount;
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

        public static class ProductDetailsBean {
            private String cat_id;
            private String cat_name;
            /**
             * _id : 60e5adc55af36c5c3605bab6
             * product_img : http://54.212.108.156:3000/api/uploads/1625747585002.png
             * product_title : Nap Now Lounger Dog Bed - Black
             * thumbnail_image : http://54.212.108.156:3000/api/uploads/1625747535197.png
             * product_price : 100
             * product_discount : 0
             * product_fav : false
             * product_rating : 5
             * product_review : 0
             */

            private List<ProductListBean> product_list;

            public String getCat_id() {
                return cat_id;
            }

            public void setCat_id(String cat_id) {
                this.cat_id = cat_id;
            }

            public String getCat_name() {
                return cat_name;
            }

            public void setCat_name(String cat_name) {
                this.cat_name = cat_name;
            }

            public List<ProductListBean> getProduct_list() {
                return product_list;
            }

            public void setProduct_list(List<ProductListBean> product_list) {
                this.product_list = product_list;
            }

            public static class ProductListBean {
                private String _id;
                private String product_img;
                private String product_title;
                private String thumbnail_image;
                private int product_price;
                private int product_discount_price;

                public int getProduct_discount_price() {
                    return product_discount_price;
                }

                public void setProduct_discount_price(int product_discount_price) {
                    this.product_discount_price = product_discount_price;
                }

                private int product_discount;
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
    }
}
