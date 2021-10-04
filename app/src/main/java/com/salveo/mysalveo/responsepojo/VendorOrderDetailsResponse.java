package com.salveo.mysalveo.responsepojo;

import java.util.List;

public class VendorOrderDetailsResponse {

    /**
     * Status : Success
     * Message : Order Details
     * Data : {"_id":"605d7d0dc63b6e29ba4e64ff","order_id":"ITEM-1616739597042","product_name":"DOG FOOD 2","product_quantity":2,"product_price":86,"prodcut_image":"http://54.212.108.156:3000/api/uploads/1615541391131.jpeg","date_of_booking":"26-03-2021 11:49 AM","status":"Cancelled","user_cancell_info":"not needs now","user_cancell_date":"26-03-2021 11:56 AM","vendor_cancell_info":"","vendor_cancell_date":"","vendor_accept_cancel":"","vendor_complete_date":"","vendor_complete_info":"","prodcut_track_details":[{"id":0,"title":"Order Booked","date":"15-03-2021 04:17 PM","Status":true},{"id":1,"title":"Order Accept","date":"15-03-2021 04:17 PM","Status":false},{"id":2,"title":"Order Dispatch","date":"15-03-2021 04:17 PM","Status":false},{"id":3,"title":"In Transit","date":"15-03-2021 04:17 PM","Status":false},{"id":4,"title":"Order Cancelled","date":"26-03-2021 11:56 AM","Status":true},{"id":5,"title":"Vendor cancelled","date":"15-03-2021 04:17 PM","Status":false}],"delivery_date":"","date_of_booking_display":"26-03-2021 11:49 AM","delivery_date_display":"","order_status":"Cancelled","prodouct_total":86,"shipping_address_id":"","billling_address_id":"","shipping_address":"","billing_address":"","shipping_charge":0,"over_all_total":86,"discount_price":0,"grand_total":86,"coupon_code":"","payment_id":"pay_GrBIOQh54RbTTt","shipping_details_id":{"_id":"605b030e5eb1ea4d142324c9","user_id":"605322f25e35b95a5cf804de","user_first_name":"sangeetha","user_last_name":"saravanan","user_flat_no":"k.k.nagar","user_stree":"malles AV garden lavnder street","user_landmark":"max fashion","user_picocode":"600125","user_state":"Tamil Nadu","user_city":"Chennai","user_mobile":"9003237255","user_alter_mobile":"1234567899","user_address_stauts":"Last Used","user_address_type":"Home","user_display_date":"24-03-2021","updatedAt":"2021-03-26T06:30:48.177Z","createdAt":"2021-03-24T09:14:54.231Z","__v":0},"shipping_details":"No :k.k.nagar, malles AV garden, Chennai, Tamil Nadu, ZIP CODE - 600125"}
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

    public static class DataBean  {
        /**
         * _id : 605d7d0dc63b6e29ba4e64ff
         * order_id : ITEM-1616739597042
         * product_name : DOG FOOD 2
         * product_quantity : 2
         * product_price : 86
         * prodcut_image : http://54.212.108.156:3000/api/uploads/1615541391131.jpeg
         * date_of_booking : 26-03-2021 11:49 AM
         * status : Cancelled
         * user_cancell_info : not needs now
         * user_cancell_date : 26-03-2021 11:56 AM
         * vendor_cancell_info :
         * vendor_cancell_date :
         * vendor_accept_cancel :
         * vendor_complete_date :
         * vendor_complete_info :
         * prodcut_track_details : [{"id":0,"title":"Order Booked","date":"15-03-2021 04:17 PM","Status":true},{"id":1,"title":"Order Accept","date":"15-03-2021 04:17 PM","Status":false},{"id":2,"title":"Order Dispatch","date":"15-03-2021 04:17 PM","Status":false},{"id":3,"title":"In Transit","date":"15-03-2021 04:17 PM","Status":false},{"id":4,"title":"Order Cancelled","date":"26-03-2021 11:56 AM","Status":true},{"id":5,"title":"Vendor cancelled","date":"15-03-2021 04:17 PM","Status":false}]
         * delivery_date :
         * date_of_booking_display : 26-03-2021 11:49 AM
         * delivery_date_display :
         * order_status : Cancelled
         * prodouct_total : 86
         * shipping_address_id :
         * billling_address_id :
         * shipping_address :
         * billing_address :
         * shipping_charge : 0
         * over_all_total : 86
         * discount_price : 0
         * grand_total : 86
         * coupon_code :
         * payment_id : pay_GrBIOQh54RbTTt
         * shipping_details_id : {"_id":"605b030e5eb1ea4d142324c9","user_id":"605322f25e35b95a5cf804de","user_first_name":"sangeetha","user_last_name":"saravanan","user_flat_no":"k.k.nagar","user_stree":"malles AV garden lavnder street","user_landmark":"max fashion","user_picocode":"600125","user_state":"Tamil Nadu","user_city":"Chennai","user_mobile":"9003237255","user_alter_mobile":"1234567899","user_address_stauts":"Last Used","user_address_type":"Home","user_display_date":"24-03-2021","updatedAt":"2021-03-26T06:30:48.177Z","createdAt":"2021-03-24T09:14:54.231Z","__v":0}
         * shipping_details : No :k.k.nagar, malles AV garden, Chennai, Tamil Nadu, ZIP CODE - 600125
         */

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
        private ShippingDetailsIdBean shipping_details_id;
        private String shipping_details;
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

        public ShippingDetailsIdBean getShipping_details_id() {
            return shipping_details_id;
        }

        public void setShipping_details_id(ShippingDetailsIdBean shipping_details_id) {
            this.shipping_details_id = shipping_details_id;

        }


        public String getShipping_details() {
            return shipping_details;
        }

        public void setShipping_details(String shipping_details) {
            this.shipping_details = shipping_details;

        }

        public List<ProdcutTrackDetailsBean> getProdcut_track_details() {
            return prodcut_track_details;
        }

        public void setProdcut_track_details(List<ProdcutTrackDetailsBean> prodcut_track_details) {
            this.prodcut_track_details = prodcut_track_details;

        }

        public static class ShippingDetailsIdBean  {
            /**
             * _id : 605b030e5eb1ea4d142324c9
             * user_id : 605322f25e35b95a5cf804de
             * user_first_name : sangeetha
             * user_last_name : saravanan
             * user_flat_no : k.k.nagar
             * user_stree : malles AV garden lavnder street
             * user_landmark : max fashion
             * user_picocode : 600125
             * user_state : Tamil Nadu
             * user_city : Chennai
             * user_mobile : 9003237255
             * user_alter_mobile : 1234567899
             * user_address_stauts : Last Used
             * user_address_type : Home
             * user_display_date : 24-03-2021
             * updatedAt : 2021-03-26T06:30:48.177Z
             * createdAt : 2021-03-24T09:14:54.231Z
             * __v : 0
             */

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




        }

        public static class ProdcutTrackDetailsBean  {
            /**
             * id : 0
             * title : Order Booked
             * date : 15-03-2021 04:17 PM
             * Status : true
             */

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