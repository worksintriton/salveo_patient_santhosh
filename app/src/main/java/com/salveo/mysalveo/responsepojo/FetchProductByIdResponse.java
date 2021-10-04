package com.salveo.mysalveo.responsepojo;

import java.util.List;

public class FetchProductByIdResponse {

    /**
     * Status : Success
     * Message : product list
     * Product_details : {"_id":"60ae2c0c48ffef65a41bc546","product_img":["http://54.212.108.156:3000/api/uploads/1625750185578.png"],"product_title":"Pedigree Vegetarian Adult Dry Food","product_price":108,"product_discount":0,"product_discount_price":0,"thumbnail_image":"http://54.212.108.156:3000/api/uploads/1625752843017.png","breed_type":[{"_id":"602d1c0c562e0916bc9b3216","pet_type_id":"602d1bf4562e0916bc9b3215","pet_breed":"Akita","date_and_time":"2/17/2021, 7:07:16 PM","delete_status":false,"updatedAt":"2021-06-11T10:18:20.754Z","createdAt":"2021-02-17T13:37:16.917Z","__v":0,"pet_breed_img":"http://54.212.108.156:3000/api/uploads/1623406696583.jpeg"}],"pet_type":[{"_id":"602d1bf4562e0916bc9b3215","pet_type_title":"Dogs - (Indian & Foreign) ","date_and_time":"2/17/2021, 7:06:51 PM","delete_status":false,"updatedAt":"2021-06-25T06:55:36.526Z","createdAt":"2021-02-17T13:36:52.141Z","__v":0,"pet_type_img":"http://54.212.108.156:3000/api/uploads/1623864181382.jpg"}],"age":[2,3],"cat_id":{"_id":"5fec1424ea832e2e73c1fc78","img_path":"http://52.25.163.13:3000/api/uploads/template%20(2).jpg","product_cate":"Pet Foods - Dry","img_index":0,"show_status":true,"date_and_time":"Thu Jul 08 2021 01:55:35 GMT+0530 (India Standard Time)","delete_status":false,"updatedAt":"2021-07-08T09:39:43.827Z","createdAt":"2020-12-30T05:46:12.099Z","__v":0},"threshould":"28","product_discription":"Your pet gets protein from the soybean and vegetable oils while the vitamins from vegetables (including Vitamin E) boosts your pooch's immune system. A balanced calcium and phosphorous ratio contributes to strong bones and joints. Ingredients: Pedigree Veg Dog Food is made with Vegetables and vegetable by-products, Vegetable oils, Iodized salt, Essential vitamins and minerals, Cereals and cereal by-products, Permitted preservatives, Antioxidants and flavours, No beef or pork. ","product_fav":true,"product_rating":5,"product_review":0,"product_related":[{"_id":"60ae2c0c48ffef65a41bc546","product_img":"http://54.212.108.156:3000/api/uploads/1625750185578.png","product_title":"Pedigree Vegetarian Adult Dry Food","product_price":108,"thumbnail_image":"http://54.212.108.156:3000/api/uploads/1625752843017.png","product_discount":0,"product_discount_price":0,"product_fav":true,"product_rating":5,"product_review":0}],"product_cart_count":0}
     * vendor_details : {"bussiness_gallery":[{"bus_service_gall":"http://54.212.108.156:3000/api/uploads/60ae1b0c48ffef65a41bc5442605152639"}],"certifi":[{"bus_certif":"http://54.212.108.156:3000/api/uploads/60ae1b0c48ffef65a41bc544certificate.pdf"}],"_id":"60ae1b6c48ffef65a41bc545","user_id":"60ae1b0c48ffef65a41bc544","user_name":"ss feed ","user_email":"test@test.com","bussiness_name":"ss feed ","bussiness_email":"test@test.com","bussiness":"SS Pet Feeds","bussiness_phone":"4564564564","business_reg":"SS Pet shop","photo_id_proof":"http://54.212.108.156:3000/api/uploads/60ae1b0c48ffef65a41bc544certificate.pdf","govt_id_proof":"http://54.212.108.156:3000/api/uploads/60ae1b0c48ffef65a41bc544certificate.pdf","date_and_time":"08-07-2021 02:47 AM","mobile_type":"IOS","profile_status":true,"profile_verification_status":"Verified","bussiness_loc":"Nungambakkam, Uthamar Gandhi Salai, Chennai, India, 600034 ","bussiness_lat":13.055580139160156,"bussiness_long":80.24905619622487,"delete_status":false,"updatedAt":"2021-07-07T21:18:14.531Z","createdAt":"2021-05-26T09:57:00.685Z","__v":0}
     * Code : 200
     */

    private String Status;
    private String Message;
    /**
     * _id : 60ae2c0c48ffef65a41bc546
     * product_img : ["http://54.212.108.156:3000/api/uploads/1625750185578.png"]
     * product_title : Pedigree Vegetarian Adult Dry Food
     * product_price : 108
     * product_discount : 0
     * product_discount_price : 0
     * thumbnail_image : http://54.212.108.156:3000/api/uploads/1625752843017.png
     * breed_type : [{"_id":"602d1c0c562e0916bc9b3216","pet_type_id":"602d1bf4562e0916bc9b3215","pet_breed":"Akita","date_and_time":"2/17/2021, 7:07:16 PM","delete_status":false,"updatedAt":"2021-06-11T10:18:20.754Z","createdAt":"2021-02-17T13:37:16.917Z","__v":0,"pet_breed_img":"http://54.212.108.156:3000/api/uploads/1623406696583.jpeg"}]
     * pet_type : [{"_id":"602d1bf4562e0916bc9b3215","pet_type_title":"Dogs - (Indian & Foreign) ","date_and_time":"2/17/2021, 7:06:51 PM","delete_status":false,"updatedAt":"2021-06-25T06:55:36.526Z","createdAt":"2021-02-17T13:36:52.141Z","__v":0,"pet_type_img":"http://54.212.108.156:3000/api/uploads/1623864181382.jpg"}]
     * age : [2,3]
     * cat_id : {"_id":"5fec1424ea832e2e73c1fc78","img_path":"http://52.25.163.13:3000/api/uploads/template%20(2).jpg","product_cate":"Pet Foods - Dry","img_index":0,"show_status":true,"date_and_time":"Thu Jul 08 2021 01:55:35 GMT+0530 (India Standard Time)","delete_status":false,"updatedAt":"2021-07-08T09:39:43.827Z","createdAt":"2020-12-30T05:46:12.099Z","__v":0}
     * threshould : 28
     * product_discription : Your pet gets protein from the soybean and vegetable oils while the vitamins from vegetables (including Vitamin E) boosts your pooch's immune system. A balanced calcium and phosphorous ratio contributes to strong bones and joints. Ingredients: Pedigree Veg Dog Food is made with Vegetables and vegetable by-products, Vegetable oils, Iodized salt, Essential vitamins and minerals, Cereals and cereal by-products, Permitted preservatives, Antioxidants and flavours, No beef or pork.
     * product_fav : true
     * product_rating : 5
     * product_review : 0
     * product_related : [{"_id":"60ae2c0c48ffef65a41bc546","product_img":"http://54.212.108.156:3000/api/uploads/1625750185578.png","product_title":"Pedigree Vegetarian Adult Dry Food","product_price":108,"thumbnail_image":"http://54.212.108.156:3000/api/uploads/1625752843017.png","product_discount":0,"product_discount_price":0,"product_fav":true,"product_rating":5,"product_review":0}]
     * product_cart_count : 0
     */

    private ProductDetailsBean Product_details;
    /**
     * bussiness_gallery : [{"bus_service_gall":"http://54.212.108.156:3000/api/uploads/60ae1b0c48ffef65a41bc5442605152639"}]
     * certifi : [{"bus_certif":"http://54.212.108.156:3000/api/uploads/60ae1b0c48ffef65a41bc544certificate.pdf"}]
     * _id : 60ae1b6c48ffef65a41bc545
     * user_id : 60ae1b0c48ffef65a41bc544
     * user_name : ss feed
     * user_email : test@test.com
     * bussiness_name : ss feed
     * bussiness_email : test@test.com
     * bussiness : SS Pet Feeds
     * bussiness_phone : 4564564564
     * business_reg : SS Pet shop
     * photo_id_proof : http://54.212.108.156:3000/api/uploads/60ae1b0c48ffef65a41bc544certificate.pdf
     * govt_id_proof : http://54.212.108.156:3000/api/uploads/60ae1b0c48ffef65a41bc544certificate.pdf
     * date_and_time : 08-07-2021 02:47 AM
     * mobile_type : IOS
     * profile_status : true
     * profile_verification_status : Verified
     * bussiness_loc : Nungambakkam, Uthamar Gandhi Salai, Chennai, India, 600034
     * bussiness_lat : 13.055580139160156
     * bussiness_long : 80.24905619622487
     * delete_status : false
     * updatedAt : 2021-07-07T21:18:14.531Z
     * createdAt : 2021-05-26T09:57:00.685Z
     * __v : 0
     */

    private VendorDetailsBean vendor_details;
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

    public ProductDetailsBean getProduct_details() {
        return Product_details;
    }

    public void setProduct_details(ProductDetailsBean Product_details) {
        this.Product_details = Product_details;
    }

    public VendorDetailsBean getVendor_details() {
        return vendor_details;
    }

    public void setVendor_details(VendorDetailsBean vendor_details) {
        this.vendor_details = vendor_details;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }

    public static class ProductDetailsBean {
        private String _id;
        private String product_title;
        private int product_price;
        private int product_discount;
        private int product_discount_price;
        private String thumbnail_image;
        /**
         * _id : 5fec1424ea832e2e73c1fc78
         * img_path : http://52.25.163.13:3000/api/uploads/template%20(2).jpg
         * product_cate : Pet Foods - Dry
         * img_index : 0
         * show_status : true
         * date_and_time : Thu Jul 08 2021 01:55:35 GMT+0530 (India Standard Time)
         * delete_status : false
         * updatedAt : 2021-07-08T09:39:43.827Z
         * createdAt : 2020-12-30T05:46:12.099Z
         * __v : 0
         */

        private CatIdBean cat_id;
        private String threshould;
        private String product_discription;
        private boolean product_fav;
        private int product_rating;
        private int product_review;
        private int product_cart_count;
        private List<String> product_img;
        /**
         * _id : 602d1c0c562e0916bc9b3216
         * pet_type_id : 602d1bf4562e0916bc9b3215
         * pet_breed : Akita
         * date_and_time : 2/17/2021, 7:07:16 PM
         * delete_status : false
         * updatedAt : 2021-06-11T10:18:20.754Z
         * createdAt : 2021-02-17T13:37:16.917Z
         * __v : 0
         * pet_breed_img : http://54.212.108.156:3000/api/uploads/1623406696583.jpeg
         */

        private List<BreedTypeBean> breed_type;
        /**
         * _id : 602d1bf4562e0916bc9b3215
         * pet_type_title : Dogs - (Indian & Foreign)
         * date_and_time : 2/17/2021, 7:06:51 PM
         * delete_status : false
         * updatedAt : 2021-06-25T06:55:36.526Z
         * createdAt : 2021-02-17T13:36:52.141Z
         * __v : 0
         * pet_type_img : http://54.212.108.156:3000/api/uploads/1623864181382.jpg
         */

        private List<PetTypeBean> pet_type;
        private List<Integer> age;
        /**
         * _id : 60ae2c0c48ffef65a41bc546
         * product_img : http://54.212.108.156:3000/api/uploads/1625750185578.png
         * product_title : Pedigree Vegetarian Adult Dry Food
         * product_price : 108
         * thumbnail_image : http://54.212.108.156:3000/api/uploads/1625752843017.png
         * product_discount : 0
         * product_discount_price : 0
         * product_fav : true
         * product_rating : 5
         * product_review : 0
         */

        private List<ProductRelatedBean> product_related;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
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

        public String getThumbnail_image() {
            return thumbnail_image;
        }

        public void setThumbnail_image(String thumbnail_image) {
            this.thumbnail_image = thumbnail_image;
        }

        public CatIdBean getCat_id() {
            return cat_id;
        }

        public void setCat_id(CatIdBean cat_id) {
            this.cat_id = cat_id;
        }

        public String getThreshould() {
            return threshould;
        }

        public void setThreshould(String threshould) {
            this.threshould = threshould;
        }

        public String getProduct_discription() {
            return product_discription;
        }

        public void setProduct_discription(String product_discription) {
            this.product_discription = product_discription;
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

        public int getProduct_cart_count() {
            return product_cart_count;
        }

        public void setProduct_cart_count(int product_cart_count) {
            this.product_cart_count = product_cart_count;
        }

        public List<String> getProduct_img() {
            return product_img;
        }

        public void setProduct_img(List<String> product_img) {
            this.product_img = product_img;
        }

        public List<BreedTypeBean> getBreed_type() {
            return breed_type;
        }

        public void setBreed_type(List<BreedTypeBean> breed_type) {
            this.breed_type = breed_type;
        }

        public List<PetTypeBean> getPet_type() {
            return pet_type;
        }

        public void setPet_type(List<PetTypeBean> pet_type) {
            this.pet_type = pet_type;
        }

        public List<Integer> getAge() {
            return age;
        }

        public void setAge(List<Integer> age) {
            this.age = age;
        }

        public List<ProductRelatedBean> getProduct_related() {
            return product_related;
        }

        public void setProduct_related(List<ProductRelatedBean> product_related) {
            this.product_related = product_related;
        }

        public static class CatIdBean {
            private String _id;
            private String img_path;
            private String product_cate;
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

            public String getProduct_cate() {
                return product_cate;
            }

            public void setProduct_cate(String product_cate) {
                this.product_cate = product_cate;
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

        public static class BreedTypeBean {
            private String _id;
            private String pet_type_id;
            private String pet_breed;
            private String date_and_time;
            private boolean delete_status;
            private String updatedAt;
            private String createdAt;
            private int __v;
            private String pet_breed_img;

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

            public String getPet_breed_img() {
                return pet_breed_img;
            }

            public void setPet_breed_img(String pet_breed_img) {
                this.pet_breed_img = pet_breed_img;
            }
        }

        public static class PetTypeBean {
            private String _id;
            private String pet_type_title;
            private String date_and_time;
            private boolean delete_status;
            private String updatedAt;
            private String createdAt;
            private int __v;
            private String pet_type_img;

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public String getPet_type_title() {
                return pet_type_title;
            }

            public void setPet_type_title(String pet_type_title) {
                this.pet_type_title = pet_type_title;
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

            public String getPet_type_img() {
                return pet_type_img;
            }

            public void setPet_type_img(String pet_type_img) {
                this.pet_type_img = pet_type_img;
            }
        }

        public static class ProductRelatedBean {
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

    public static class VendorDetailsBean {
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
         * bus_service_gall : http://54.212.108.156:3000/api/uploads/60ae1b0c48ffef65a41bc5442605152639
         */

        private List<BussinessGalleryBean> bussiness_gallery;
        /**
         * bus_certif : http://54.212.108.156:3000/api/uploads/60ae1b0c48ffef65a41bc544certificate.pdf
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
            private String bus_service_gall;

            public String getBus_service_gall() {
                return bus_service_gall;
            }

            public void setBus_service_gall(String bus_service_gall) {
                this.bus_service_gall = bus_service_gall;
            }
        }

        public static class CertifiBean {
            private String bus_certif;

            public String getBus_certif() {
                return bus_certif;
            }

            public void setBus_certif(String bus_certif) {
                this.bus_certif = bus_certif;
            }
        }
    }
}
