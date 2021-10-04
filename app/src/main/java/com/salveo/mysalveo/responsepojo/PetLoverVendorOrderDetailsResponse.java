package com.salveo.mysalveo.responsepojo;

import java.util.List;

public class PetLoverVendorOrderDetailsResponse {


    /**
     * Status : Success
     * Message : Vendor Product Grouped
     * Data : {"order_details":{"order_id":"ORDER-1619068388640","order_product":4,"order_status":"Cancelled","order_text":"Food Products","order_payment_id":"pay_H1qa3DlDt40yjQ","order_price":571,"order_booked":"22-04-2021 10:43 AM","order_image":"http://54.212.108.156:3000/api/uploads/1617098535464.jpeg","order_cancelled":"","order_completed":""},"shipping_address":{"_id":"60797c16a20ca32d2668a30c","user_id":"603e27792c2b43125f8cb802","user_first_name":"DINESHKUMAR","user_last_name":"Deva","user_flat_no":"4/3","user_stree":"Marriyamman Kovil street","user_landmark":"","user_picocode":"621006","user_state":"TAMILNADU","user_city":"TRICHY","user_mobile":"9842670813","user_alter_mobile":"","user_address_stauts":"Last Used","user_address_type":"Home","user_display_date":"16-04-2021","updatedAt":"2021-04-16T11:59:18.437Z","createdAt":"2021-04-16T11:59:18.437Z","__v":0},"product_details":[{"product_id":0,"product_image":"http://54.212.108.156:3000/api/uploads/1617098535464.jpeg","product_name":"Pedigree","product_count":1,"product_price":100,"product_discount":0,"product_total_price":100,"product_total_discount":0,"product_stauts":"Order Cancelled","product_booked":"22-04-2021 10:43 AM"},{"product_id":1,"product_image":"http://54.212.108.156:3000/api/uploads/1616648074881.jpeg","product_name":"Cow","product_count":1,"product_price":72,"product_discount":20,"product_total_price":72,"product_total_discount":20,"product_stauts":"Order Cancelled","product_booked":"22-04-2021 10:43 AM"},{"product_id":2,"product_image":"http://54.212.108.156:3000/api/uploads/1615541442124.jpeg","product_name":"CAT FOOD 2","product_count":1,"product_price":319,"product_discount":2,"product_total_price":319,"product_total_discount":2,"product_stauts":"Order Cancelled","product_booked":"22-04-2021 10:43 AM"},{"product_id":3,"product_image":"http://54.212.108.156:3000/api/uploads/1615541391131.jpeg","product_name":"DOG FOOD 2","product_count":1,"product_price":80,"product_discount":20,"product_total_price":80,"product_total_discount":20,"product_stauts":"Order Cancelled","product_booked":"22-04-2021 10:43 AM"}]}
     * Code : 200
     */

    private String Status;
    private String Message;
    /**
     * order_details : {"order_id":"ORDER-1619068388640","order_product":4,"order_status":"Cancelled","order_text":"Food Products","order_payment_id":"pay_H1qa3DlDt40yjQ","order_price":571,"order_booked":"22-04-2021 10:43 AM","order_image":"http://54.212.108.156:3000/api/uploads/1617098535464.jpeg","order_cancelled":"","order_completed":""}
     * shipping_address : {"_id":"60797c16a20ca32d2668a30c","user_id":"603e27792c2b43125f8cb802","user_first_name":"DINESHKUMAR","user_last_name":"Deva","user_flat_no":"4/3","user_stree":"Marriyamman Kovil street","user_landmark":"","user_picocode":"621006","user_state":"TAMILNADU","user_city":"TRICHY","user_mobile":"9842670813","user_alter_mobile":"","user_address_stauts":"Last Used","user_address_type":"Home","user_display_date":"16-04-2021","updatedAt":"2021-04-16T11:59:18.437Z","createdAt":"2021-04-16T11:59:18.437Z","__v":0}
     * product_details : [{"product_id":0,"product_image":"http://54.212.108.156:3000/api/uploads/1617098535464.jpeg","product_name":"Pedigree","product_count":1,"product_price":100,"product_discount":0,"product_total_price":100,"product_total_discount":0,"product_stauts":"Order Cancelled","product_booked":"22-04-2021 10:43 AM"},{"product_id":1,"product_image":"http://54.212.108.156:3000/api/uploads/1616648074881.jpeg","product_name":"Cow","product_count":1,"product_price":72,"product_discount":20,"product_total_price":72,"product_total_discount":20,"product_stauts":"Order Cancelled","product_booked":"22-04-2021 10:43 AM"},{"product_id":2,"product_image":"http://54.212.108.156:3000/api/uploads/1615541442124.jpeg","product_name":"CAT FOOD 2","product_count":1,"product_price":319,"product_discount":2,"product_total_price":319,"product_total_discount":2,"product_stauts":"Order Cancelled","product_booked":"22-04-2021 10:43 AM"},{"product_id":3,"product_image":"http://54.212.108.156:3000/api/uploads/1615541391131.jpeg","product_name":"DOG FOOD 2","product_count":1,"product_price":80,"product_discount":20,"product_total_price":80,"product_total_discount":20,"product_stauts":"Order Cancelled","product_booked":"22-04-2021 10:43 AM"}]
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
        /**
         * order_id : ORDER-1619068388640
         * order_product : 4
         * order_status : Cancelled
         * order_text : Food Products
         * order_payment_id : pay_H1qa3DlDt40yjQ
         * order_price : 571
         * order_booked : 22-04-2021 10:43 AM
         * order_image : http://54.212.108.156:3000/api/uploads/1617098535464.jpeg
         * order_cancelled :
         * order_completed :
         */

        private OrderDetailsBean order_details;
        /**
         * _id : 60797c16a20ca32d2668a30c
         * user_id : 603e27792c2b43125f8cb802
         * user_first_name : DINESHKUMAR
         * user_last_name : Deva
         * user_flat_no : 4/3
         * user_stree : Marriyamman Kovil street
         * user_landmark :
         * user_picocode : 621006
         * user_state : TAMILNADU
         * user_city : TRICHY
         * user_mobile : 9842670813
         * user_alter_mobile :
         * user_address_stauts : Last Used
         * user_address_type : Home
         * user_display_date : 16-04-2021
         * updatedAt : 2021-04-16T11:59:18.437Z
         * createdAt : 2021-04-16T11:59:18.437Z
         * __v : 0
         */

        private ShippingAddressBean shipping_address;
        /**
         * product_id : 0
         * product_image : http://54.212.108.156:3000/api/uploads/1617098535464.jpeg
         * product_name : Pedigree
         * product_count : 1
         * product_price : 100
         * product_discount : 0
         * product_total_price : 100
         * product_total_discount : 0
         * product_stauts : Order Cancelled
         * product_booked : 22-04-2021 10:43 AM
         */

        private List<ProductDetailsBean> product_details;

        public OrderDetailsBean getOrder_details() {
            return order_details;
        }

        public void setOrder_details(OrderDetailsBean order_details) {
            this.order_details = order_details;
        }

        public ShippingAddressBean getShipping_address() {
            return shipping_address;
        }

        public void setShipping_address(ShippingAddressBean shipping_address) {
            this.shipping_address = shipping_address;
        }

        public List<ProductDetailsBean> getProduct_details() {
            return product_details;
        }

        public void setProduct_details(List<ProductDetailsBean> product_details) {
            this.product_details = product_details;
        }

        public static class OrderDetailsBean {
            private String order_id;
            private int order_product;
            private String order_status;
            private String order_text;
            private String order_payment_id;
            private int order_price;
            private String order_booked;
            private String order_image;
            private String order_cancelled;
            private String order_completed;

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public int getOrder_product() {
                return order_product;
            }

            public void setOrder_product(int order_product) {
                this.order_product = order_product;
            }

            public String getOrder_status() {
                return order_status;
            }

            public void setOrder_status(String order_status) {
                this.order_status = order_status;
            }

            public String getOrder_text() {
                return order_text;
            }

            public void setOrder_text(String order_text) {
                this.order_text = order_text;
            }

            public String getOrder_payment_id() {
                return order_payment_id;
            }

            public void setOrder_payment_id(String order_payment_id) {
                this.order_payment_id = order_payment_id;
            }

            public int getOrder_price() {
                return order_price;
            }

            public void setOrder_price(int order_price) {
                this.order_price = order_price;
            }

            public String getOrder_booked() {
                return order_booked;
            }

            public void setOrder_booked(String order_booked) {
                this.order_booked = order_booked;
            }

            public String getOrder_image() {
                return order_image;
            }

            public void setOrder_image(String order_image) {
                this.order_image = order_image;
            }

            public String getOrder_cancelled() {
                return order_cancelled;
            }

            public void setOrder_cancelled(String order_cancelled) {
                this.order_cancelled = order_cancelled;
            }

            public String getOrder_completed() {
                return order_completed;
            }

            public void setOrder_completed(String order_completed) {
                this.order_completed = order_completed;
            }
        }

        public static class ShippingAddressBean {
            private String _id;
            private String user_id;
            private String user_first_name;
            private String user_last_name;
            private String user_flat_no;
            private String user_stree;
            private String user_landmark;
            private String user_picocode;
            private String user_state;
            private String user_city;
            private String user_mobile;
            private String user_alter_mobile;
            private String user_address_stauts;
            private String user_address_type;
            private String user_display_date;
            private String updatedAt;
            private String createdAt;
            private int __v;

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

            public String getUser_first_name() {
                return user_first_name;
            }

            public void setUser_first_name(String user_first_name) {
                this.user_first_name = user_first_name;
            }

            public String getUser_last_name() {
                return user_last_name;
            }

            public void setUser_last_name(String user_last_name) {
                this.user_last_name = user_last_name;
            }

            public String getUser_flat_no() {
                return user_flat_no;
            }

            public void setUser_flat_no(String user_flat_no) {
                this.user_flat_no = user_flat_no;
            }

            public String getUser_stree() {
                return user_stree;
            }

            public void setUser_stree(String user_stree) {
                this.user_stree = user_stree;
            }

            public String getUser_landmark() {
                return user_landmark;
            }

            public void setUser_landmark(String user_landmark) {
                this.user_landmark = user_landmark;
            }

            public String getUser_picocode() {
                return user_picocode;
            }

            public void setUser_picocode(String user_picocode) {
                this.user_picocode = user_picocode;
            }

            public String getUser_state() {
                return user_state;
            }

            public void setUser_state(String user_state) {
                this.user_state = user_state;
            }

            public String getUser_city() {
                return user_city;
            }

            public void setUser_city(String user_city) {
                this.user_city = user_city;
            }

            public String getUser_mobile() {
                return user_mobile;
            }

            public void setUser_mobile(String user_mobile) {
                this.user_mobile = user_mobile;
            }

            public String getUser_alter_mobile() {
                return user_alter_mobile;
            }

            public void setUser_alter_mobile(String user_alter_mobile) {
                this.user_alter_mobile = user_alter_mobile;
            }

            public String getUser_address_stauts() {
                return user_address_stauts;
            }

            public void setUser_address_stauts(String user_address_stauts) {
                this.user_address_stauts = user_address_stauts;
            }

            public String getUser_address_type() {
                return user_address_type;
            }

            public void setUser_address_type(String user_address_type) {
                this.user_address_type = user_address_type;
            }

            public String getUser_display_date() {
                return user_display_date;
            }

            public void setUser_display_date(String user_display_date) {
                this.user_display_date = user_display_date;
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

        public static class ProductDetailsBean {
            private int product_id;
            private String product_image;
            private String product_name;
            private int product_count;
            private int product_price;
            private int product_discount;
            private int product_total_price;
            private int product_total_discount;
            private String product_stauts;
            private String product_booked;

            public int getProduct_id() {
                return product_id;
            }

            public void setProduct_id(int product_id) {
                this.product_id = product_id;
            }

            public String getProduct_image() {
                return product_image;
            }

            public void setProduct_image(String product_image) {
                this.product_image = product_image;
            }

            public String getProduct_name() {
                return product_name;
            }

            public void setProduct_name(String product_name) {
                this.product_name = product_name;
            }

            public int getProduct_count() {
                return product_count;
            }

            public void setProduct_count(int product_count) {
                this.product_count = product_count;
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

            public int getProduct_total_price() {
                return product_total_price;
            }

            public void setProduct_total_price(int product_total_price) {
                this.product_total_price = product_total_price;
            }

            public int getProduct_total_discount() {
                return product_total_discount;
            }

            public void setProduct_total_discount(int product_total_discount) {
                this.product_total_discount = product_total_discount;
            }

            public String getProduct_stauts() {
                return product_stauts;
            }

            public void setProduct_stauts(String product_stauts) {
                this.product_stauts = product_stauts;
            }

            public String getProduct_booked() {
                return product_booked;
            }

            public void setProduct_booked(String product_booked) {
                this.product_booked = product_booked;
            }
        }
    }
}
