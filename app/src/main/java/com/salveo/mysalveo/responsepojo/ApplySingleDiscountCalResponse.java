package com.salveo.mysalveo.responsepojo;

public class ApplySingleDiscountCalResponse {

    /**
     * Status : Success
     * Message : product details screen  Updated
     * Data : {"cost":"486","discount":0,"discount_amount":2,"discount_status":false,"discount_cal":"488","discount_start_date":"","discount_end_date":"","today_deal":true}
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
         * cost : 486
         * discount : 0
         * discount_amount : 2
         * discount_status : false
         * discount_cal : 488
         * discount_start_date :
         * discount_end_date :
         * today_deal : true
         */

        private String cost;
        private int discount;
        private int discount_amount;
        private boolean discount_status;
        private String discount_cal;
        private String discount_start_date;
        private String discount_end_date;
        private boolean today_deal;


        public String getCost() {
            return cost;
        }

        public void setCost(String cost) {
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

        public String getDiscount_cal() {
            return discount_cal;
        }

        public void setDiscount_cal(String discount_cal) {
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


        public boolean isToday_deal() {
            return today_deal;
        }

        public void setToday_deal(boolean today_deal) {
            this.today_deal = today_deal;

        }
    }
}
