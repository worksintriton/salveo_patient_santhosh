package com.salveo.mysalveo.responsepojo;

import java.util.List;

public class VendorCancelsOrderResponse {


    /**
     * Status : Success
     * Message : Order Booked Updated successfully
     * Data : {"product_data":[{"__v":"0","_id":"604b384842cb073ec4dfef15","createdAt":"2021-03-12T09:45:44.571Z","product_count":1,"product_id":{"__v":"0","_id":"604b34a242cb073ec4dfef12","age":[1,2],"breed_type":["602d1c0c562e0916bc9b3216","602d1c17562e0916bc9b3217","602d1c20562e0916bc9b3218","602d1c29562e0916bc9b3219","602d1c3b562e0916bc9b321a","602d1c45562e0916bc9b321b","602d1c58562e0916bc9b321c","602d1c86562e0916bc9b321e","602d1c97562e0916bc9b321f","602d1cb5562e0916bc9b3220","602d1cc2562e0916bc9b3221","602d1cda562e0916bc9b3222","602d1ce5562e0916bc9b3223","602d1d03562e0916bc9b3225","602d1d17562e0916bc9b3226","602d1d35562e0916bc9b3227","602d1d55562e0916bc9b3228","602d1d68562e0916bc9b3229","602d1d84562e0916bc9b322b","602d1d8d562e0916bc9b322c","602d1d99562e0916bc9b322d","602d1da3562e0916bc9b322e","602d1dac562e0916bc9b322f","602d1db8562e0916bc9b3230","602d1dc7562e0916bc9b3231","602d1e91562e0916bc9b3233","602d1ea0562e0916bc9b3234","602d1efc562e0916bc9b3235","602d1f1a562e0916bc9b3239","602d1f25562e0916bc9b323a","602d1f35562e0916bc9b323b","602d1f47562e0916bc9b323c","602d1f60562e0916bc9b323e","602d1f6e562e0916bc9b323f","602d1f78562e0916bc9b3240","602d1f82562e0916bc9b3241","602d1f8f562e0916bc9b3242","602d1f9a562e0916bc9b3243","603dd32c5757f179716e8488"],"cat_id":"5fec1424ea832e2e73c1fc78","cost":100,"count":0,"createdAt":"2021-03-12T09:30:10.998Z","date_and_time":"Fri Mar 12 2021 15:00:10 GMT+0530 (India Standard Time)","delete_status":true,"discount":12,"discount_amount":0,"fav_status":false,"mobile_type":"Admin","pet_type":["602d1bf4562e0916bc9b3215","602d1c6b562e0916bc9b321d","602d1cf4562e0916bc9b3224","602d1d73562e0916bc9b322a","602d1dd5562e0916bc9b3232","602d1f52562e0916bc9b323d"],"product_discription":"this is dog food 2","product_img":["http://54.212.108.156:3000/api/uploads/1615541391131.jpeg","http://54.212.108.156:3000/api/uploads/1615541404050.jpeg"],"product_name":"DOG FOOD 2","related":"","status":"true","threshould":"10","today_deal":false,"updatedAt":"2021-03-12T09:30:10.998Z","user_id":"604866a50b3a487571a1c568","verification_status":"Not Verified"},"updatedAt":"2021-03-12T09:45:49.043Z","user_id":"604081d12c2b43125f8cb840"}],"prodcut_track_details":[{"id":1,"title":"Order booked","date":"12-03-2021 03:16 PM"},{"id":7,"title":"Vendor cancelled","date":"11-03-2021 03:07 PM"}],"_id":"604b387942cb073ec4dfef16","user_id":"604081d12c2b43125f8cb840","order_id":"ITEM-1615542393826","vendor_id":"604866a50b3a487571a1c568","product_id":"604b34a242cb073ec4dfef12","product_quantity":1,"date_of_booking":"12-03-2021 03:16 PM","delivery_date":"","date_of_booking_display":"12-03-2021 03:16 PM","delivery_date_display":"","order_status":"cancelled","prodouct_total":100,"shipping_address_id":"","billling_address_id":"","shipping_address":"","billing_address":"","shipping_charge":0,"over_all_total":100,"discount_price":0,"grand_total":100,"coupon_code":"","delete_status":false,"user_cancell_info":"","user_cancell_date":"","user_return_info":"","user_return_date":"","user_return_pic":"","vendor_cancell_info":"We don't have stock in our company","vendor_cancell_date":"We don't have stock in our company","vendor_accept_cancel":"","vendor_accept_cancel_date":"","vendor_complete_date":"","vendor_complete_info":"","payment_id":"pay_GlhKnypTPnSzMr","updatedAt":"2021-03-12T13:56:25.694Z","createdAt":"2021-03-12T09:46:33.828Z","__v":0}
     * Code : 200
     */

    private String Status;
    private String Message;
    /**
     * product_data : [{"__v":"0","_id":"604b384842cb073ec4dfef15","createdAt":"2021-03-12T09:45:44.571Z","product_count":1,"product_id":{"__v":"0","_id":"604b34a242cb073ec4dfef12","age":[1,2],"breed_type":["602d1c0c562e0916bc9b3216","602d1c17562e0916bc9b3217","602d1c20562e0916bc9b3218","602d1c29562e0916bc9b3219","602d1c3b562e0916bc9b321a","602d1c45562e0916bc9b321b","602d1c58562e0916bc9b321c","602d1c86562e0916bc9b321e","602d1c97562e0916bc9b321f","602d1cb5562e0916bc9b3220","602d1cc2562e0916bc9b3221","602d1cda562e0916bc9b3222","602d1ce5562e0916bc9b3223","602d1d03562e0916bc9b3225","602d1d17562e0916bc9b3226","602d1d35562e0916bc9b3227","602d1d55562e0916bc9b3228","602d1d68562e0916bc9b3229","602d1d84562e0916bc9b322b","602d1d8d562e0916bc9b322c","602d1d99562e0916bc9b322d","602d1da3562e0916bc9b322e","602d1dac562e0916bc9b322f","602d1db8562e0916bc9b3230","602d1dc7562e0916bc9b3231","602d1e91562e0916bc9b3233","602d1ea0562e0916bc9b3234","602d1efc562e0916bc9b3235","602d1f1a562e0916bc9b3239","602d1f25562e0916bc9b323a","602d1f35562e0916bc9b323b","602d1f47562e0916bc9b323c","602d1f60562e0916bc9b323e","602d1f6e562e0916bc9b323f","602d1f78562e0916bc9b3240","602d1f82562e0916bc9b3241","602d1f8f562e0916bc9b3242","602d1f9a562e0916bc9b3243","603dd32c5757f179716e8488"],"cat_id":"5fec1424ea832e2e73c1fc78","cost":100,"count":0,"createdAt":"2021-03-12T09:30:10.998Z","date_and_time":"Fri Mar 12 2021 15:00:10 GMT+0530 (India Standard Time)","delete_status":true,"discount":12,"discount_amount":0,"fav_status":false,"mobile_type":"Admin","pet_type":["602d1bf4562e0916bc9b3215","602d1c6b562e0916bc9b321d","602d1cf4562e0916bc9b3224","602d1d73562e0916bc9b322a","602d1dd5562e0916bc9b3232","602d1f52562e0916bc9b323d"],"product_discription":"this is dog food 2","product_img":["http://54.212.108.156:3000/api/uploads/1615541391131.jpeg","http://54.212.108.156:3000/api/uploads/1615541404050.jpeg"],"product_name":"DOG FOOD 2","related":"","status":"true","threshould":"10","today_deal":false,"updatedAt":"2021-03-12T09:30:10.998Z","user_id":"604866a50b3a487571a1c568","verification_status":"Not Verified"},"updatedAt":"2021-03-12T09:45:49.043Z","user_id":"604081d12c2b43125f8cb840"}]
     * prodcut_track_details : [{"id":1,"title":"Order booked","date":"12-03-2021 03:16 PM"},{"id":7,"title":"Vendor cancelled","date":"11-03-2021 03:07 PM"}]
     * _id : 604b387942cb073ec4dfef16
     * user_id : 604081d12c2b43125f8cb840
     * order_id : ITEM-1615542393826
     * vendor_id : 604866a50b3a487571a1c568
     * product_id : 604b34a242cb073ec4dfef12
     * product_quantity : 1
     * date_of_booking : 12-03-2021 03:16 PM
     * delivery_date :
     * date_of_booking_display : 12-03-2021 03:16 PM
     * delivery_date_display :
     * order_status : cancelled
     * prodouct_total : 100
     * shipping_address_id :
     * billling_address_id :
     * shipping_address :
     * billing_address :
     * shipping_charge : 0
     * over_all_total : 100
     * discount_price : 0
     * grand_total : 100
     * coupon_code :
     * delete_status : false
     * user_cancell_info :
     * user_cancell_date :
     * user_return_info :
     * user_return_date :
     * user_return_pic :
     * vendor_cancell_info : We don't have stock in our company
     * vendor_cancell_date : We don't have stock in our company
     * vendor_accept_cancel :
     * vendor_accept_cancel_date :
     * vendor_complete_date :
     * vendor_complete_info :
     * payment_id : pay_GlhKnypTPnSzMr
     * updatedAt : 2021-03-12T13:56:25.694Z
     * createdAt : 2021-03-12T09:46:33.828Z
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
        private String order_id;
        private String vendor_id;
        private String product_id;
        private int product_quantity;
        private String date_of_booking;
        private String delivery_date;
        private String date_of_booking_display;
        private String delivery_date_display;
        private String order_status;
        private int prodouct_total;
        private String shipping_address_id;
        private String billling_address_id;
        private String shipping_address;
        private String billing_address;
        private int shipping_charge;
        private int over_all_total;
        private int discount_price;
        private int grand_total;
        private String coupon_code;
        private boolean delete_status;
        private String user_cancell_info;
        private String user_cancell_date;
        private String user_return_info;
        private String user_return_date;
        private String user_return_pic;
        private String vendor_cancell_info;
        private String vendor_cancell_date;
        private String vendor_accept_cancel;
        private String vendor_accept_cancel_date;
        private String vendor_complete_date;
        private String vendor_complete_info;
        private String payment_id;
        private String updatedAt;
        private String createdAt;
        private int __v;
        /**
         * __v : 0
         * _id : 604b384842cb073ec4dfef15
         * createdAt : 2021-03-12T09:45:44.571Z
         * product_count : 1
         * product_id : {"__v":"0","_id":"604b34a242cb073ec4dfef12","age":[1,2],"breed_type":["602d1c0c562e0916bc9b3216","602d1c17562e0916bc9b3217","602d1c20562e0916bc9b3218","602d1c29562e0916bc9b3219","602d1c3b562e0916bc9b321a","602d1c45562e0916bc9b321b","602d1c58562e0916bc9b321c","602d1c86562e0916bc9b321e","602d1c97562e0916bc9b321f","602d1cb5562e0916bc9b3220","602d1cc2562e0916bc9b3221","602d1cda562e0916bc9b3222","602d1ce5562e0916bc9b3223","602d1d03562e0916bc9b3225","602d1d17562e0916bc9b3226","602d1d35562e0916bc9b3227","602d1d55562e0916bc9b3228","602d1d68562e0916bc9b3229","602d1d84562e0916bc9b322b","602d1d8d562e0916bc9b322c","602d1d99562e0916bc9b322d","602d1da3562e0916bc9b322e","602d1dac562e0916bc9b322f","602d1db8562e0916bc9b3230","602d1dc7562e0916bc9b3231","602d1e91562e0916bc9b3233","602d1ea0562e0916bc9b3234","602d1efc562e0916bc9b3235","602d1f1a562e0916bc9b3239","602d1f25562e0916bc9b323a","602d1f35562e0916bc9b323b","602d1f47562e0916bc9b323c","602d1f60562e0916bc9b323e","602d1f6e562e0916bc9b323f","602d1f78562e0916bc9b3240","602d1f82562e0916bc9b3241","602d1f8f562e0916bc9b3242","602d1f9a562e0916bc9b3243","603dd32c5757f179716e8488"],"cat_id":"5fec1424ea832e2e73c1fc78","cost":100,"count":0,"createdAt":"2021-03-12T09:30:10.998Z","date_and_time":"Fri Mar 12 2021 15:00:10 GMT+0530 (India Standard Time)","delete_status":true,"discount":12,"discount_amount":0,"fav_status":false,"mobile_type":"Admin","pet_type":["602d1bf4562e0916bc9b3215","602d1c6b562e0916bc9b321d","602d1cf4562e0916bc9b3224","602d1d73562e0916bc9b322a","602d1dd5562e0916bc9b3232","602d1f52562e0916bc9b323d"],"product_discription":"this is dog food 2","product_img":["http://54.212.108.156:3000/api/uploads/1615541391131.jpeg","http://54.212.108.156:3000/api/uploads/1615541404050.jpeg"],"product_name":"DOG FOOD 2","related":"","status":"true","threshould":"10","today_deal":false,"updatedAt":"2021-03-12T09:30:10.998Z","user_id":"604866a50b3a487571a1c568","verification_status":"Not Verified"}
         * updatedAt : 2021-03-12T09:45:49.043Z
         * user_id : 604081d12c2b43125f8cb840
         */

        private List<ProductDataBean> product_data;
        /**
         * id : 1
         * title : Order booked
         * date : 12-03-2021 03:16 PM
         */

        private List<ProdcutTrackDetailsBean> prodcut_track_details;

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

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getVendor_id() {
            return vendor_id;
        }

        public void setVendor_id(String vendor_id) {
            this.vendor_id = vendor_id;
        }

        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }

        public int getProduct_quantity() {
            return product_quantity;
        }

        public void setProduct_quantity(int product_quantity) {
            this.product_quantity = product_quantity;
        }

        public String getDate_of_booking() {
            return date_of_booking;
        }

        public void setDate_of_booking(String date_of_booking) {
            this.date_of_booking = date_of_booking;
        }

        public String getDelivery_date() {
            return delivery_date;
        }

        public void setDelivery_date(String delivery_date) {
            this.delivery_date = delivery_date;
        }

        public String getDate_of_booking_display() {
            return date_of_booking_display;
        }

        public void setDate_of_booking_display(String date_of_booking_display) {
            this.date_of_booking_display = date_of_booking_display;
        }

        public String getDelivery_date_display() {
            return delivery_date_display;
        }

        public void setDelivery_date_display(String delivery_date_display) {
            this.delivery_date_display = delivery_date_display;
        }

        public String getOrder_status() {
            return order_status;
        }

        public void setOrder_status(String order_status) {
            this.order_status = order_status;
        }

        public int getProdouct_total() {
            return prodouct_total;
        }

        public void setProdouct_total(int prodouct_total) {
            this.prodouct_total = prodouct_total;
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

        public int getShipping_charge() {
            return shipping_charge;
        }

        public void setShipping_charge(int shipping_charge) {
            this.shipping_charge = shipping_charge;
        }

        public int getOver_all_total() {
            return over_all_total;
        }

        public void setOver_all_total(int over_all_total) {
            this.over_all_total = over_all_total;
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

        public String getCoupon_code() {
            return coupon_code;
        }

        public void setCoupon_code(String coupon_code) {
            this.coupon_code = coupon_code;
        }

        public boolean isDelete_status() {
            return delete_status;
        }

        public void setDelete_status(boolean delete_status) {
            this.delete_status = delete_status;
        }

        public String getUser_cancell_info() {
            return user_cancell_info;
        }

        public void setUser_cancell_info(String user_cancell_info) {
            this.user_cancell_info = user_cancell_info;
        }

        public String getUser_cancell_date() {
            return user_cancell_date;
        }

        public void setUser_cancell_date(String user_cancell_date) {
            this.user_cancell_date = user_cancell_date;
        }

        public String getUser_return_info() {
            return user_return_info;
        }

        public void setUser_return_info(String user_return_info) {
            this.user_return_info = user_return_info;
        }

        public String getUser_return_date() {
            return user_return_date;
        }

        public void setUser_return_date(String user_return_date) {
            this.user_return_date = user_return_date;
        }

        public String getUser_return_pic() {
            return user_return_pic;
        }

        public void setUser_return_pic(String user_return_pic) {
            this.user_return_pic = user_return_pic;
        }

        public String getVendor_cancell_info() {
            return vendor_cancell_info;
        }

        public void setVendor_cancell_info(String vendor_cancell_info) {
            this.vendor_cancell_info = vendor_cancell_info;
        }

        public String getVendor_cancell_date() {
            return vendor_cancell_date;
        }

        public void setVendor_cancell_date(String vendor_cancell_date) {
            this.vendor_cancell_date = vendor_cancell_date;
        }

        public String getVendor_accept_cancel() {
            return vendor_accept_cancel;
        }

        public void setVendor_accept_cancel(String vendor_accept_cancel) {
            this.vendor_accept_cancel = vendor_accept_cancel;
        }

        public String getVendor_accept_cancel_date() {
            return vendor_accept_cancel_date;
        }

        public void setVendor_accept_cancel_date(String vendor_accept_cancel_date) {
            this.vendor_accept_cancel_date = vendor_accept_cancel_date;
        }

        public String getVendor_complete_date() {
            return vendor_complete_date;
        }

        public void setVendor_complete_date(String vendor_complete_date) {
            this.vendor_complete_date = vendor_complete_date;
        }

        public String getVendor_complete_info() {
            return vendor_complete_info;
        }

        public void setVendor_complete_info(String vendor_complete_info) {
            this.vendor_complete_info = vendor_complete_info;
        }

        public String getPayment_id() {
            return payment_id;
        }

        public void setPayment_id(String payment_id) {
            this.payment_id = payment_id;
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

        public List<ProductDataBean> getProduct_data() {
            return product_data;
        }

        public void setProduct_data(List<ProductDataBean> product_data) {
            this.product_data = product_data;
        }

        public List<ProdcutTrackDetailsBean> getProdcut_track_details() {
            return prodcut_track_details;
        }

        public void setProdcut_track_details(List<ProdcutTrackDetailsBean> prodcut_track_details) {
            this.prodcut_track_details = prodcut_track_details;
        }

        public static class ProductDataBean {
            private String __v;
            private String _id;
            private String createdAt;
            private int product_count;
            /**
             * __v : 0
             * _id : 604b34a242cb073ec4dfef12
             * age : [1,2]
             * breed_type : ["602d1c0c562e0916bc9b3216","602d1c17562e0916bc9b3217","602d1c20562e0916bc9b3218","602d1c29562e0916bc9b3219","602d1c3b562e0916bc9b321a","602d1c45562e0916bc9b321b","602d1c58562e0916bc9b321c","602d1c86562e0916bc9b321e","602d1c97562e0916bc9b321f","602d1cb5562e0916bc9b3220","602d1cc2562e0916bc9b3221","602d1cda562e0916bc9b3222","602d1ce5562e0916bc9b3223","602d1d03562e0916bc9b3225","602d1d17562e0916bc9b3226","602d1d35562e0916bc9b3227","602d1d55562e0916bc9b3228","602d1d68562e0916bc9b3229","602d1d84562e0916bc9b322b","602d1d8d562e0916bc9b322c","602d1d99562e0916bc9b322d","602d1da3562e0916bc9b322e","602d1dac562e0916bc9b322f","602d1db8562e0916bc9b3230","602d1dc7562e0916bc9b3231","602d1e91562e0916bc9b3233","602d1ea0562e0916bc9b3234","602d1efc562e0916bc9b3235","602d1f1a562e0916bc9b3239","602d1f25562e0916bc9b323a","602d1f35562e0916bc9b323b","602d1f47562e0916bc9b323c","602d1f60562e0916bc9b323e","602d1f6e562e0916bc9b323f","602d1f78562e0916bc9b3240","602d1f82562e0916bc9b3241","602d1f8f562e0916bc9b3242","602d1f9a562e0916bc9b3243","603dd32c5757f179716e8488"]
             * cat_id : 5fec1424ea832e2e73c1fc78
             * cost : 100
             * count : 0
             * createdAt : 2021-03-12T09:30:10.998Z
             * date_and_time : Fri Mar 12 2021 15:00:10 GMT+0530 (India Standard Time)
             * delete_status : true
             * discount : 12
             * discount_amount : 0
             * fav_status : false
             * mobile_type : Admin
             * pet_type : ["602d1bf4562e0916bc9b3215","602d1c6b562e0916bc9b321d","602d1cf4562e0916bc9b3224","602d1d73562e0916bc9b322a","602d1dd5562e0916bc9b3232","602d1f52562e0916bc9b323d"]
             * product_discription : this is dog food 2
             * product_img : ["http://54.212.108.156:3000/api/uploads/1615541391131.jpeg","http://54.212.108.156:3000/api/uploads/1615541404050.jpeg"]
             * product_name : DOG FOOD 2
             * related :
             * status : true
             * threshould : 10
             * today_deal : false
             * updatedAt : 2021-03-12T09:30:10.998Z
             * user_id : 604866a50b3a487571a1c568
             * verification_status : Not Verified
             */

            private ProductIdBean product_id;
            private String updatedAt;
            private String user_id;

            public String get__v() {
                return __v;
            }

            public void set__v(String __v) {
                this.__v = __v;
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
                private String __v;
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

                public String get__v() {
                    return __v;
                }

                public void set__v(String __v) {
                    this.__v = __v;
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

        public static class ProdcutTrackDetailsBean {
            private int id;
            private String title;
            private String date;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }
        }
    }
}
