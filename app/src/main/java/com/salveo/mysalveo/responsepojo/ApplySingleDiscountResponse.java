package com.salveo.mysalveo.responsepojo;

import java.util.List;

public class ApplySingleDiscountResponse {


    /**
     * Status : Success
     * Message : product details screen  Updated
     * Data : {"breed_type":["602d1cb5562e0916bc9b3220"],"pet_type":["602d1c6b562e0916bc9b321d"],"age":[2],"product_img":["http://54.212.108.156:3000/api/uploads/1616062517150.jpeg"],"_id":"605328895e35b95a5cf804e4","user_id":"6053188d167149502f84ec26","cat_id":"5fec14a5ea832e2e73c1fc79","threshould":"3","product_name":"Bush Betta Fish Food 20 gms","product_discription":"Cat Food Adult Tuna & Salmon\n\n","related":"","count":0,"status":"true","verification_status":"Not Verified","date_and_time":"Thu Mar 18 2021 15:46:42 GMT+0530 (India Standard Time)","mobile_type":"Admin","delete_status":true,"fav_status":false,"today_deal":true,"cost":498,"discount":0,"discount_amount":2,"discount_status":false,"discount_cal":500,"discount_start_date":"","discount_end_date":"","updatedAt":"2021-03-23T03:44:45.651Z","createdAt":"2021-03-18T10:16:42.004Z","__v":0}
     * Code : 200
     */

    private String Status;
    private String Message;
    /**
     * breed_type : ["602d1cb5562e0916bc9b3220"]
     * pet_type : ["602d1c6b562e0916bc9b321d"]
     * age : [2]
     * product_img : ["http://54.212.108.156:3000/api/uploads/1616062517150.jpeg"]
     * _id : 605328895e35b95a5cf804e4
     * user_id : 6053188d167149502f84ec26
     * cat_id : 5fec14a5ea832e2e73c1fc79
     * threshould : 3
     * product_name : Bush Betta Fish Food 20 gms
     * product_discription : Cat Food Adult Tuna & Salmon
     * related :
     * count : 0
     * status : true
     * verification_status : Not Verified
     * date_and_time : Thu Mar 18 2021 15:46:42 GMT+0530 (India Standard Time)
     * mobile_type : Admin
     * delete_status : true
     * fav_status : false
     * today_deal : true
     * cost : 498
     * discount : 0
     * discount_amount : 2
     * discount_status : false
     * discount_cal : 500
     * discount_start_date :
     * discount_end_date :
     * updatedAt : 2021-03-23T03:44:45.651Z
     * createdAt : 2021-03-18T10:16:42.004Z
     * __v : 0
     */

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
        private String _id;
        private String user_id;
        private String cat_id;
        private String threshould;
        private String product_name;
        private String product_discription;
        private String related;
        private int count;
        private String status;
        private String verification_status;
        private String date_and_time;
        private String mobile_type;
        private boolean delete_status;
        private boolean fav_status;
        private boolean today_deal;
        private int cost;
        private int discount;
        private int discount_amount;
        private boolean discount_status;
        private int discount_cal;
        private String discount_start_date;
        private String discount_end_date;
        private String updatedAt;
        private String createdAt;
        private int __v;
        private List<String> breed_type;
        private List<String> pet_type;
        private List<Integer> age;
        private List<String> product_img;

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

        public int getCost() {
            return cost;
        }

        public void setCost(int cost) {
            this.cost = cost;
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

        public boolean isDiscount_status() {
            return discount_status;
        }

        public void setDiscount_status(boolean discount_status) {
            this.discount_status = discount_status;
        }

        public int getDiscount_cal() {
            return discount_cal;
        }

        public void setDiscount_cal(int discount_cal) {
            this.discount_cal = discount_cal;
        }

        public String getDiscount_start_date() {
            return discount_start_date;
        }

        public void setDiscount_start_date(String discount_start_date) {
            this.discount_start_date = discount_start_date;
        }

        public String getDiscount_end_date() {
            return discount_end_date;
        }

        public void setDiscount_end_date(String discount_end_date) {
            this.discount_end_date = discount_end_date;
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
