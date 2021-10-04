package com.salveo.mysalveo.responsepojo;

import java.io.Serializable;
import java.util.List;

public class CartDetailsResponse {


    /**
     * Status : Success
     * Message : product categories screen Deleted successfully
     * Data : [{"_id":"6046fa59cb48ca0b68cda50c","user_id":"603e27792c2b43125f8cb802","product_id":{"breed_type":["602d1c20562e0916bc9b3218"],"pet_type":["602d1c6b562e0916bc9b321d"],"age":[3],"product_img":["http://54.212.108.156:3000/api/uploads/1614075552394.jpg"],"_id":"6034d6a5888af7628e7e17d4","user_id":"602a2061b3c2dd2c152d77d8","cat_id":"5fec14a5ea832e2e73c1fc79","cost":1000,"threshould":"100","product_name":"Cat Dinner","product_discription":"This cat  food","discount":10,"related":"","count":0,"status":"true","verification_status":"Not Verified","date_and_time":"Tue Feb 23 2021 15:49:15 GMT+0530 (India Standard Time)","mobile_type":"Admin","delete_status":true,"fav_status":false,"today_deal":true,"updatedAt":"2021-03-08T09:15:24.812Z","createdAt":"2021-02-23T10:19:17.691Z","__v":0},"product_count":7,"updatedAt":"2021-03-09T06:10:04.116Z","createdAt":"2021-03-09T04:32:25.151Z","__v":0},{"_id":"60471192760fff2968288bbd","user_id":"603e27792c2b43125f8cb802","product_id":{"breed_type":["602d1c17562e0916bc9b3217"],"pet_type":["602d1c6b562e0916bc9b321d"],"age":[3],"product_img":["http://54.212.108.156:3000/api/uploads/1614075490400.jpg"],"_id":"6034d66598fa826140f6a3a3","user_id":"602a2061b3c2dd2c152d77d8","cat_id":"5fec14a5ea832e2e73c1fc79","cost":40000,"threshould":"100","product_name":"CAT Lunch","product_discription":"This is cat lunch","discount":40,"related":"","count":0,"status":"true","verification_status":"Not Verified","date_and_time":"Tue Feb 23 2021 15:48:14 GMT+0530 (India Standard Time)","mobile_type":"Admin","delete_status":true,"fav_status":false,"today_deal":true,"updatedAt":"2021-03-08T09:15:22.710Z","createdAt":"2021-02-23T10:18:13.989Z","__v":0},"product_count":1,"updatedAt":"2021-03-09T06:11:30.904Z","createdAt":"2021-03-09T06:11:30.904Z","__v":0}]
     * prodouct_total : 47000
     * shipping_charge : 0
     * discount_price : 0
     * grand_total : 0
     * Code : 200
     *  prodcut_count: 1,
     *  prodcut_item_count: 1,
     *  "date_of_booking_display" : "23-Jan-2020",
     *             "date_of_booking" : "23-10-2021  11 : 00 PM",
     *             "coupon_code" : "",
     *              "shipping_address_id" : "",
     *             "billling_address_id" : "",
     *             "shipping_address" : "",
     *              "billing_address" : "",
     */


    private String user_id;
    private String shipping_details_id;

    public String getShipping_details_id() {
        return shipping_details_id;
    }

    public void setShipping_details_id(String shipping_details_id) {
        this.shipping_details_id = shipping_details_id;
    }

    private String Status;
    private String Message;
    private int prodouct_total;
    private int shipping_charge;
    private int discount_price;
    private int grand_total;
    private int prodcut_count;
    private int prodcut_item_count;
    private String date_of_booking_display;
    private String date_of_booking;
    private String coupon_code;
    private String shipping_address_id;
    private String billling_address_id;
    private String shipping_address;
    private String billing_address;
    private String payment_id ;

    public String getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(String payment_id) {
        this.payment_id = payment_id;
    }

    private int Code;
    private List<DataBean> Data;



    public String getDate_of_booking_display() {
        return date_of_booking_display;
    }

    public void setDate_of_booking_display(String date_of_booking_display) {
        this.date_of_booking_display = date_of_booking_display;
    }

    public String getDate_of_booking() {
        return date_of_booking;
    }

    public void setDate_of_booking(String date_of_booking) {
        this.date_of_booking = date_of_booking;
    }

    public String getCoupon_code() {
        return coupon_code;
    }

    public void setCoupon_code(String coupon_code) {
        this.coupon_code = coupon_code;
    }

    public String getShipping_address_id() {
        return shipping_address_id;
    }

    public void setShipping_address_id(String shipping_address_id) {
        this.shipping_address_id = shipping_address_id;
    }

    public String getBillling_address_id() {
        return billling_address_id;
    }

    public void setBillling_address_id(String billling_address_id) {
        this.billling_address_id = billling_address_id;
    }

    public String getShipping_address() {
        return shipping_address;
    }

    public void setShipping_address(String shipping_address) {
        this.shipping_address = shipping_address;
    }

    public String getBilling_address() {
        return billing_address;
    }

    public void setBilling_address(String billing_address) {
        this.billing_address = billing_address;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getProdcut_count() {
        return prodcut_count;
    }

    public void setProdcut_count(int prodcut_count) {
        this.prodcut_count = prodcut_count;
    }

    public int getProdcut_item_count() {
        return prodcut_item_count;
    }

    public void setProdcut_item_count(int prodcut_item_count) {
        this.prodcut_item_count = prodcut_item_count;
    }

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


    public int getProdouct_total() {
        return prodouct_total;
    }

    public void setProdouct_total(int prodouct_total) {
        this.prodouct_total = prodouct_total;

    }


    public int getShipping_charge() {
        return shipping_charge;
    }

    public void setShipping_charge(int shipping_charge) {
        this.shipping_charge = shipping_charge;

    }


    public int getDiscount_price() {
        return discount_price;
    }

    public void setDiscount_price(int discount_price) {
        this.discount_price = discount_price;

    }


    public int getGrand_total() {
        return grand_total;
    }

    public void setGrand_total(int grand_total) {
        this.grand_total = grand_total;

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

    public static class DataBean implements Serializable {
        /**
         * _id : 6046fa59cb48ca0b68cda50c
         * user_id : 603e27792c2b43125f8cb802
         * product_id : {"breed_type":["602d1c20562e0916bc9b3218"],"pet_type":["602d1c6b562e0916bc9b321d"],"age":[3],"product_img":["http://54.212.108.156:3000/api/uploads/1614075552394.jpg"],"_id":"6034d6a5888af7628e7e17d4","user_id":"602a2061b3c2dd2c152d77d8","cat_id":"5fec14a5ea832e2e73c1fc79","cost":1000,"threshould":"100","product_name":"Cat Dinner","product_discription":"This cat  food","discount":10,"related":"","count":0,"status":"true","verification_status":"Not Verified","date_and_time":"Tue Feb 23 2021 15:49:15 GMT+0530 (India Standard Time)","mobile_type":"Admin","delete_status":true,"fav_status":false,"today_deal":true,"updatedAt":"2021-03-08T09:15:24.812Z","createdAt":"2021-02-23T10:19:17.691Z","__v":0}
         * product_count : 7
         * updatedAt : 2021-03-09T06:10:04.116Z
         * createdAt : 2021-03-09T04:32:25.151Z
         * __v : 0
         */

        private String _id;
        private String user_id;
        private ProductIdBean product_id;
        private int product_count;
        private String updatedAt;
        private String createdAt;
        private String __v;


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


        public ProductIdBean getProduct_id() {
            return product_id;
        }

        public void setProduct_id(ProductIdBean product_id) {
            this.product_id = product_id;

        }


        public int getProduct_count() {
            return product_count;
        }

        public void setProduct_count(int product_count) {
            this.product_count = product_count;
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


        public String get__v() {
            return __v;
        }

        public void set__v(String __v) {
            this.__v = __v;
        }



        public static class ProductIdBean implements Serializable{
            /**
             * breed_type : ["602d1c20562e0916bc9b3218"]
             * pet_type : ["602d1c6b562e0916bc9b321d"]
             * age : [3]
             * product_img : ["http://54.212.108.156:3000/api/uploads/1614075552394.jpg"]
             * _id : 6034d6a5888af7628e7e17d4
             * user_id : 602a2061b3c2dd2c152d77d8
             * cat_id : 5fec14a5ea832e2e73c1fc79
             * cost : 1000
             * discount_amount : 1000
             * threshould : 100
             * product_name : Cat Dinner
             * product_discription : This cat  food
             * discount : 10
             * related :
             * count : 0
             * status : true
             * verification_status : Not Verified
             * date_and_time : Tue Feb 23 2021 15:49:15 GMT+0530 (India Standard Time)
             * mobile_type : Admin
             * delete_status : true
             * fav_status : false
             * today_deal : true
             * updatedAt : 2021-03-08T09:15:24.812Z
             * createdAt : 2021-02-23T10:19:17.691Z
             * __v : 0
             */

            private String _id;
            private String user_id;
            private String cat_id;
            private int cost;
            private String threshould;
            private String product_name;
            private String product_discription;
            private int discount;
            private String related;
            private int count;
            private int discount_amount;
            private String status;
            private String verification_status;
            private String date_and_time;
            private String mobile_type;
            private boolean delete_status;
            private boolean fav_status;
            private boolean today_deal;
            private String updatedAt;
            private String createdAt;
            private String __v;
            private List<String> breed_type;
            private List<String> pet_type;
            private List<Integer> age;
            private List<String> product_img;

            private String thumbnail_image;
            public String getThumbnail_image() {
                return thumbnail_image;
            }

            public void setThumbnail_image(String thumbnail_image) {
                this.thumbnail_image = thumbnail_image;
            }

            public int getDiscount_amount() {
                return discount_amount;
            }

            public void setDiscount_amount(int discount_amount) {
                this.discount_amount = discount_amount;
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

            public String getCat_id() {
                return cat_id;
            }

            public void setCat_id(String cat_id) {
                this.cat_id = cat_id;
            }

            public int getCost() {
                return cost;
            }

            public void setCost(int cost) {
                this.cost = cost;
            }

            public String getThreshould() {
                return threshould;
            }

            public void setThreshould(String threshould) {
                this.threshould = threshould;
            }

            public String getProduct_name() {
                return product_name;
            }

            public void setProduct_name(String product_name) {
                this.product_name = product_name;
            }


            public String getProduct_discription() {
                return product_discription;
            }

            public void setProduct_discription(String product_discription) {
                this.product_discription = product_discription;
            }

            public int getDiscount() {
                return discount;
            }

            public void setDiscount(int discount) {
                this.discount = discount;
            }


            public String getRelated() {
                return related;
            }

            public void setRelated(String related) {
                this.related = related;

            }


            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;

            }


            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;

            }


            public String getVerification_status() {
                return verification_status;
            }

            public void setVerification_status(String verification_status) {
                this.verification_status = verification_status;

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


            public boolean isFav_status() {
                return fav_status;
            }

            public void setFav_status(boolean fav_status) {
                this.fav_status = fav_status;

            }


            public boolean isToday_deal() {
                return today_deal;
            }

            public void setToday_deal(boolean today_deal) {
                this.today_deal = today_deal;

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

            public String get__v() {
                return __v;
            }

            public void set__v(String __v) {
                this.__v = __v;
            }




            public List<String> getBreed_type() {
                return breed_type;
            }

            public void setBreed_type(List<String> breed_type) {
                this.breed_type = breed_type;
            }


            public List<String> getPet_type() {
                return pet_type;
            }

            public void setPet_type(List<String> pet_type) {
                this.pet_type = pet_type;

            }


            public List<Integer> getAge() {
                return age;
            }

            public void setAge(List<Integer> age) {
                this.age = age;

            }


            public List<String> getProduct_img() {
                return product_img;
            }

            public void setProduct_img(List<String> product_img) {
                this.product_img = product_img;

            }
        }
    }
}
