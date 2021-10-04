package com.salveo.mysalveo.responsepojo;

import java.util.List;

public class DoctorMyCalendarAvlDaysResponse {

    /**
     * Status : Success
     * Message : Calendar Details
     * Data : [{"Title":"Monday","Status":false},{"Title":"Tuesday","Status":false},{"Title":"Wednesday","Status":false},{"Title":"Thursday","Status":false},{"Title":"Firday","Status":false},{"Title":"Saturday","Status":false},{"Title":"Sundau","Status":false}]
     * Code : 200
     */

    private String Status;
    private String Message;
    private int Code;
    private List<DataBean> Data;

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

    public static class DataBean {
        /**
         * Title : Monday
         * Status : false
         */

        private String Title;
        private boolean Status;

        public String getTitle() {
            return Title;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }

        public boolean isStatus() {
            return Status;
        }

        public void setStatus(boolean Status) {
            this.Status = Status;
        }
    }
}
