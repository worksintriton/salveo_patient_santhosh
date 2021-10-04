package com.salveo.mysalveo.responsepojo;

public class CartSuccessResponse {


    /**
     * Status : Success
     * Message : Order Booked successfully
     * Data : {"Booking_id":"ITEM-1616144579643","prodouct_total":450,"discount_price":0,"grand_total":450,"date_of_booking_display":"19-03-2021 02:32 PM"}
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
         * Booking_id : ITEM-1616144579643
         * prodouct_total : 450
         * discount_price : 0
         * grand_total : 450
         * date_of_booking_display : 19-03-2021 02:32 PM
         */

        private String Booking_id;
        private int prodouct_total;
        private int discount_price;
        private int grand_total;
        private String date_of_booking_display;


        public String getBooking_id() {
            return Booking_id;
        }

        public void setBooking_id(String Booking_id) {
            this.Booking_id = Booking_id;

        }


        public int getProdouct_total() {
            return prodouct_total;
        }

        public void setProdouct_total(int prodouct_total) {
            this.prodouct_total = prodouct_total;

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


        public String getDate_of_booking_display() {
            return date_of_booking_display;
        }

        public void setDate_of_booking_display(String date_of_booking_display) {
            this.date_of_booking_display = date_of_booking_display;

        }
    }
}
