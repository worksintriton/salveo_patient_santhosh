package com.salveo.mysalveo.responsepojo;

public class NotificationCartCountResponse {

    /**
     * Status : Success
     * Message : Product Count List
     * Data : {"notification_count":0,"product_count":9}
     * Code : 200
     */

    private String Status;
    private String Message;
    /**
     * notification_count : 0
     * product_count : 9
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
        private int notification_count;
        private int product_count;

        public int getNotification_count() {
            return notification_count;
        }

        public void setNotification_count(int notification_count) {
            this.notification_count = notification_count;
        }

        public int getProduct_count() {
            return product_count;
        }

        public void setProduct_count(int product_count) {
            this.product_count = product_count;
        }
    }
}
