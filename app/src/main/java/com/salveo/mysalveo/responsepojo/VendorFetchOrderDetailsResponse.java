package com.salveo.mysalveo.responsepojo;

import java.util.List;

public class VendorFetchOrderDetailsResponse {


    /**
     * Status : Success
     * Message : Order Details
     * Data : {"_id":"604f6162cdef38628f812fc1","order_id":"ITEM-1615815010601","product_name":"DOG FOOD 2","product_quantity":1,"product_price":100,"prodcut_image":"http://54.212.108.156:3000/api/uploads/1615541391131.jpeg","date_of_booking":"15-03-2021 07:00 PM","status":"cancelled","user_cancell_info":"","user_cancell_date":"","vendor_cancell_info":"We don't have stock in our company","vendor_cancell_date":"15-03-2021 07:02 PM","vendor_accept_cancel":"","vendor_complete_date":"","vendor_complete_info":"","prodcut_track_details":[{"id":0,"title":"Order Booked","date":"15-03-2021 04:17 PM","Status":true},{"id":1,"title":"Order Accept","date":"15-03-2021 04:17 PM","Status":false},{"id":2,"title":"Order Dispatch","date":"15-03-2021 04:17 PM","Status":false},{"id":3,"title":"In Transit","date":"15-03-2021 04:17 PM","Status":false},{"id":4,"title":"Order Cancelled","date":"15-03-2021 04:17 PM","Status":false},{"id":5,"title":"Vendor cancelled","date":"15-03-2021 07:02 PM","Status":true}],"delivery_date":"","date_of_booking_display":"15-03-2021 07:00 PM","delivery_date_display":"","order_status":"cancelled","prodouct_total":100,"shipping_address_id":"","billling_address_id":"","shipping_address":"","billing_address":"","shipping_charge":0,"over_all_total":100,"discount_price":0,"grand_total":100,"coupon_code":"","payment_id":"pay_GmwkXbR3GOA0qC"}
     * Code : 200
     */

    private String Status;
    private String Message;
    /**
     * _id : 604f6162cdef38628f812fc1
     * order_id : ITEM-1615815010601
     * product_name : DOG FOOD 2
     * product_quantity : 1
     * product_price : 100
     * prodcut_image : http://54.212.108.156:3000/api/uploads/1615541391131.jpeg
     * date_of_booking : 15-03-2021 07:00 PM
     * status : cancelled
     * user_cancell_info :
     * user_cancell_date :
     * vendor_cancell_info : We don't have stock in our company
     * vendor_cancell_date : 15-03-2021 07:02 PM
     * vendor_accept_cancel :
     * vendor_complete_date :
     * vendor_complete_info :
     * prodcut_track_details : [{"id":0,"title":"Order Booked","date":"15-03-2021 04:17 PM","Status":true},{"id":1,"title":"Order Accept","date":"15-03-2021 04:17 PM","Status":false},{"id":2,"title":"Order Dispatch","date":"15-03-2021 04:17 PM","Status":false},{"id":3,"title":"In Transit","date":"15-03-2021 04:17 PM","Status":false},{"id":4,"title":"Order Cancelled","date":"15-03-2021 04:17 PM","Status":false},{"id":5,"title":"Vendor cancelled","date":"15-03-2021 07:02 PM","Status":true}]
     * delivery_date :
     * date_of_booking_display : 15-03-2021 07:00 PM
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
     * payment_id : pay_GmwkXbR3GOA0qC
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
        private String order_id;
        private String product_name;
        private int product_quantity;
        private int product_price;
        private String prodcut_image;
        private String date_of_booking;
        private String status;
        private String user_cancell_info;
        private String user_cancell_date;
        private String vendor_cancell_info;
        private String vendor_cancell_date;
        private String vendor_accept_cancel;
        private String vendor_complete_date;
        private String vendor_complete_info;
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
        private String payment_id;
        /**
         * id : 0
         * title : Order Booked
         * date : 15-03-2021 04:17 PM
         * Status : true
         */

        private List<ProdcutTrackDetailsBean> prodcut_track_details;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getProduct_name() {
            return product_name;
        }

        public void setProduct_name(String product_name) {
            this.product_name = product_name;
        }

        public int getProduct_quantity() {
            return product_quantity;
        }

        public void setProduct_quantity(int product_quantity) {
            this.product_quantity = product_quantity;
        }

        public int getProduct_price() {
            return product_price;
        }

        public void setProduct_price(int product_price) {
            this.product_price = product_price;
        }

        public String getProdcut_image() {
            return prodcut_image;
        }

        public void setProdcut_image(String prodcut_image) {
            this.prodcut_image = prodcut_image;
        }

        public String getDate_of_booking() {
            return date_of_booking;
        }

        public void setDate_of_booking(String date_of_booking) {
            this.date_of_booking = date_of_booking;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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

        public String getPayment_id() {
            return payment_id;
        }

        public void setPayment_id(String payment_id) {
            this.payment_id = payment_id;
        }

        public List<ProdcutTrackDetailsBean> getProdcut_track_details() {
            return prodcut_track_details;
        }

        public void setProdcut_track_details(List<ProdcutTrackDetailsBean> prodcut_track_details) {
            this.prodcut_track_details = prodcut_track_details;
        }

        public static class ProdcutTrackDetailsBean {
            private int id;
            private String title;
            private String date;
            private boolean Status;

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

            public boolean isStatus() {
                return Status;
            }

            public void setStatus(boolean Status) {
                this.Status = Status;
            }
        }
    }
}
