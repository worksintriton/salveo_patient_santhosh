package com.salveo.mysalveo.requestpojo;

import java.util.List;

public class VendorOrderBookingCreateRequest {

    /**
     * Data : [{"_v":"0","_id":"60587059817d8046d6c82a23","createdAt":"2021-03-22T10:24:25.860Z","product_count":1,"product_id":{"_v":"0","_id":"604b34a242cb073ec4dfef12","age":[1,2],"breed_type":["602d1c0c562e0916bc9b3216","602d1c17562e0916bc9b3217","602d1c20562e0916bc9b3218","602d1c29562e0916bc9b3219","602d1c3b562e0916bc9b321a","602d1c45562e0916bc9b321b","602d1c58562e0916bc9b321c","602d1c86562e0916bc9b321e","602d1c97562e0916bc9b321f","602d1cb5562e0916bc9b3220","602d1cc2562e0916bc9b3221","602d1cda562e0916bc9b3222","602d1ce5562e0916bc9b3223","602d1d03562e0916bc9b3225","602d1d17562e0916bc9b3226","602d1d35562e0916bc9b3227","602d1d55562e0916bc9b3228","602d1d68562e0916bc9b3229","602d1d84562e0916bc9b322b","602d1d8d562e0916bc9b322c","602d1d99562e0916bc9b322d","602d1da3562e0916bc9b322e","602d1dac562e0916bc9b322f","602d1db8562e0916bc9b3230","602d1dc7562e0916bc9b3231","602d1e91562e0916bc9b3233","602d1ea0562e0916bc9b3234","602d1efc562e0916bc9b3235","602d1f1a562e0916bc9b3239","602d1f25562e0916bc9b323a","602d1f35562e0916bc9b323b","602d1f47562e0916bc9b323c","602d1f60562e0916bc9b323e","602d1f6e562e0916bc9b323f","602d1f78562e0916bc9b3240","602d1f82562e0916bc9b3241","602d1f8f562e0916bc9b3242","602d1f9a562e0916bc9b3243","603dd32c5757f179716e8488"],"cat_id":"5fec1424ea832e2e73c1fc78","cost":100,"count":0,"createdAt":"2021-03-12T09:30:10.998Z","date_and_time":"Fri Mar 12 2021 15:00:10 GMT+0530 (India Standard Time)","delete_status":true,"discount":2,"discount_amount":100,"fav_status":false,"mobile_type":"Admin","pet_type":["602d1bf4562e0916bc9b3215","602d1c6b562e0916bc9b321d","602d1cf4562e0916bc9b3224","602d1d73562e0916bc9b322a","602d1dd5562e0916bc9b3232","602d1f52562e0916bc9b323d"],"product_discription":"this is dog food 2","product_img":["http://54.212.108.156:3000/api/uploads/1615541391131.jpeg","http://54.212.108.156:3000/api/uploads/1615541404050.jpeg"],"product_name":"DOG FOOD 2","related":"","status":"true","threshould":"89","today_deal":false,"updatedAt":"2021-03-22T09:58:39.653Z","user_id":"604866a50b3a487571a1c568","verification_status":"Not Verified"},"updatedAt":"2021-03-22T10:24:25.860Z","user_id":"6048589d0b3a487571a1c567"}]
     * coupon_code :
     * date_of_booking : 22-03-2021 03:54 PM
     * date_of_booking_display : 22-03-2021 03:54 PM
     * discount_price : 0
     * grand_total : 100
     * payment_id : pay_GpfKU5sXF6UYpq
     * prodcut_count : 1
     * prodcut_item_count : 1
     * prodouct_total : 100
     * shipping_details_id : 60587225344d9b55ceeec259
     * shipping_charge : 0
     * user_id : 6048589d0b3a487571a1c567
     */

    private String coupon_code;
    private String date_of_booking;
    private String date_of_booking_display;
    private int discount_price;
    private int grand_total;
    private String payment_id;
    private int prodcut_count;
    private int prodcut_item_count;
    private int prodouct_total;
    private String shipping_details_id;
    private int shipping_charge;
    private String user_id;
    /**
     * _v : 0
     * _id : 60587059817d8046d6c82a23
     * createdAt : 2021-03-22T10:24:25.860Z
     * product_count : 1
     * product_id : {"_v":"0","_id":"604b34a242cb073ec4dfef12","age":[1,2],"breed_type":["602d1c0c562e0916bc9b3216","602d1c17562e0916bc9b3217","602d1c20562e0916bc9b3218","602d1c29562e0916bc9b3219","602d1c3b562e0916bc9b321a","602d1c45562e0916bc9b321b","602d1c58562e0916bc9b321c","602d1c86562e0916bc9b321e","602d1c97562e0916bc9b321f","602d1cb5562e0916bc9b3220","602d1cc2562e0916bc9b3221","602d1cda562e0916bc9b3222","602d1ce5562e0916bc9b3223","602d1d03562e0916bc9b3225","602d1d17562e0916bc9b3226","602d1d35562e0916bc9b3227","602d1d55562e0916bc9b3228","602d1d68562e0916bc9b3229","602d1d84562e0916bc9b322b","602d1d8d562e0916bc9b322c","602d1d99562e0916bc9b322d","602d1da3562e0916bc9b322e","602d1dac562e0916bc9b322f","602d1db8562e0916bc9b3230","602d1dc7562e0916bc9b3231","602d1e91562e0916bc9b3233","602d1ea0562e0916bc9b3234","602d1efc562e0916bc9b3235","602d1f1a562e0916bc9b3239","602d1f25562e0916bc9b323a","602d1f35562e0916bc9b323b","602d1f47562e0916bc9b323c","602d1f60562e0916bc9b323e","602d1f6e562e0916bc9b323f","602d1f78562e0916bc9b3240","602d1f82562e0916bc9b3241","602d1f8f562e0916bc9b3242","602d1f9a562e0916bc9b3243","603dd32c5757f179716e8488"],"cat_id":"5fec1424ea832e2e73c1fc78","cost":100,"count":0,"createdAt":"2021-03-12T09:30:10.998Z","date_and_time":"Fri Mar 12 2021 15:00:10 GMT+0530 (India Standard Time)","delete_status":true,"discount":2,"discount_amount":100,"fav_status":false,"mobile_type":"Admin","pet_type":["602d1bf4562e0916bc9b3215","602d1c6b562e0916bc9b321d","602d1cf4562e0916bc9b3224","602d1d73562e0916bc9b322a","602d1dd5562e0916bc9b3232","602d1f52562e0916bc9b323d"],"product_discription":"this is dog food 2","product_img":["http://54.212.108.156:3000/api/uploads/1615541391131.jpeg","http://54.212.108.156:3000/api/uploads/1615541404050.jpeg"],"product_name":"DOG FOOD 2","related":"","status":"true","threshould":"89","today_deal":false,"updatedAt":"2021-03-22T09:58:39.653Z","user_id":"604866a50b3a487571a1c568","verification_status":"Not Verified"}
     * updatedAt : 2021-03-22T10:24:25.860Z
     * user_id : 6048589d0b3a487571a1c567
     */

    private List<DataBean> Data;

    public String getCoupon_code() {
        return coupon_code;
    }

    public void setCoupon_code(String coupon_code) {
        this.coupon_code = coupon_code;
    }

    public String getDate_of_booking() {
        return date_of_booking;
    }

    public void setDate_of_booking(String date_of_booking) {
        this.date_of_booking = date_of_booking;
    }

    public String getDate_of_booking_display() {
        return date_of_booking_display;
    }

    public void setDate_of_booking_display(String date_of_booking_display) {
        this.date_of_booking_display = date_of_booking_display;
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

    public String getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(String payment_id) {
        this.payment_id = payment_id;
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

    public int getProdouct_total() {
        return prodouct_total;
    }

    public void setProdouct_total(int prodouct_total) {
        this.prodouct_total = prodouct_total;
    }

    public String getShipping_details_id() {
        return shipping_details_id;
    }

    public void setShipping_details_id(String shipping_details_id) {
        this.shipping_details_id = shipping_details_id;
    }

    public int getShipping_charge() {
        return shipping_charge;
    }

    public void setShipping_charge(int shipping_charge) {
        this.shipping_charge = shipping_charge;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        private String _v;
        private String _id;
        private String createdAt;
        private int product_count;
        /**
         * _v : 0
         * _id : 604b34a242cb073ec4dfef12
         * age : [1,2]
         * breed_type : ["602d1c0c562e0916bc9b3216","602d1c17562e0916bc9b3217","602d1c20562e0916bc9b3218","602d1c29562e0916bc9b3219","602d1c3b562e0916bc9b321a","602d1c45562e0916bc9b321b","602d1c58562e0916bc9b321c","602d1c86562e0916bc9b321e","602d1c97562e0916bc9b321f","602d1cb5562e0916bc9b3220","602d1cc2562e0916bc9b3221","602d1cda562e0916bc9b3222","602d1ce5562e0916bc9b3223","602d1d03562e0916bc9b3225","602d1d17562e0916bc9b3226","602d1d35562e0916bc9b3227","602d1d55562e0916bc9b3228","602d1d68562e0916bc9b3229","602d1d84562e0916bc9b322b","602d1d8d562e0916bc9b322c","602d1d99562e0916bc9b322d","602d1da3562e0916bc9b322e","602d1dac562e0916bc9b322f","602d1db8562e0916bc9b3230","602d1dc7562e0916bc9b3231","602d1e91562e0916bc9b3233","602d1ea0562e0916bc9b3234","602d1efc562e0916bc9b3235","602d1f1a562e0916bc9b3239","602d1f25562e0916bc9b323a","602d1f35562e0916bc9b323b","602d1f47562e0916bc9b323c","602d1f60562e0916bc9b323e","602d1f6e562e0916bc9b323f","602d1f78562e0916bc9b3240","602d1f82562e0916bc9b3241","602d1f8f562e0916bc9b3242","602d1f9a562e0916bc9b3243","603dd32c5757f179716e8488"]
         * cat_id : 5fec1424ea832e2e73c1fc78
         * cost : 100
         * count : 0
         * createdAt : 2021-03-12T09:30:10.998Z
         * date_and_time : Fri Mar 12 2021 15:00:10 GMT+0530 (India Standard Time)
         * delete_status : true
         * discount : 2
         * discount_amount : 100
         * fav_status : false
         * mobile_type : Admin
         * pet_type : ["602d1bf4562e0916bc9b3215","602d1c6b562e0916bc9b321d","602d1cf4562e0916bc9b3224","602d1d73562e0916bc9b322a","602d1dd5562e0916bc9b3232","602d1f52562e0916bc9b323d"]
         * product_discription : this is dog food 2
         * product_img : ["http://54.212.108.156:3000/api/uploads/1615541391131.jpeg","http://54.212.108.156:3000/api/uploads/1615541404050.jpeg"]
         * product_name : DOG FOOD 2
         * related :
         * status : true
         * threshould : 89
         * today_deal : false
         * updatedAt : 2021-03-22T09:58:39.653Z
         * user_id : 604866a50b3a487571a1c568
         * verification_status : Not Verified
         */

        private ProductIdBean product_id;
        private String updatedAt;
        private String user_id;

        public String get_v() {
            return _v;
        }

        public void set_v(String _v) {
            this._v = _v;
        }

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public int getProduct_count() {
            return product_count;
        }

        public void setProduct_count(int product_count) {
            this.product_count = product_count;
        }

        public ProductIdBean getProduct_id() {
            return product_id;
        }

        public void setProduct_id(ProductIdBean product_id) {
            this.product_id = product_id;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public static class ProductIdBean {
            private String _v;
            private String _id;
            private String cat_id;
            private int cost;
            private int count;
            private String createdAt;
            private String date_and_time;
            private boolean delete_status;
            private int discount;
            private int discount_amount;
            private boolean fav_status;
            private String mobile_type;
            private String product_discription;
            private String product_name;
            private String related;
            private String status;
            private String threshould;
            private boolean today_deal;
            private String updatedAt;
            private String user_id;
            private String verification_status;
            private List<Integer> age;
            private List<String> breed_type;
            private List<String> pet_type;
            private List<String> product_img;

            public String get_v() {
                return _v;
            }

            public void set_v(String _v) {
                this._v = _v;
            }

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
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

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
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

            public int getDiscount() {
                return discount;
            }

            public void setDiscount(int discount) {
                this.discount = discount;
            }

            public int getDiscount_amount() {
                return discount_amount;
            }

            public void setDiscount_amount(int discount_amount) {
                this.discount_amount = discount_amount;
            }

            public boolean isFav_status() {
                return fav_status;
            }

            public void setFav_status(boolean fav_status) {
                this.fav_status = fav_status;
            }

            public String getMobile_type() {
                return mobile_type;
            }

            public void setMobile_type(String mobile_type) {
                this.mobile_type = mobile_type;
            }

            public String getProduct_discription() {
                return product_discription;
            }

            public void setProduct_discription(String product_discription) {
                this.product_discription = product_discription;
            }

            public String getProduct_name() {
                return product_name;
            }

            public void setProduct_name(String product_name) {
                this.product_name = product_name;
            }

            public String getRelated() {
                return related;
            }

            public void setRelated(String related) {
                this.related = related;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getThreshould() {
                return threshould;
            }

            public void setThreshould(String threshould) {
                this.threshould = threshould;
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

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getVerification_status() {
                return verification_status;
            }

            public void setVerification_status(String verification_status) {
                this.verification_status = verification_status;
            }

            public List<Integer> getAge() {
                return age;
            }

            public void setAge(List<Integer> age) {
                this.age = age;
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

            public List<String> getProduct_img() {
                return product_img;
            }

            public void setProduct_img(List<String> product_img) {
                this.product_img = product_img;
            }
        }
    }
}
