package com.salveo.mysalveo.responsepojo;

public class EmailOTPResponse {

    /**
     * Status : Success
     * Message : Eamil id Send successfully
     * Data : {"email_id":"mohammedimthi2395@gmail.com","otp":912364}
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

    public static class DataBean {
        /**
         * email_id : mohammedimthi2395@gmail.com
         * otp : 912364
         */

        private String email_id;
        private int otp;


        public String getEmail_id() {
            return email_id;
        }

        public void setEmail_id(String email_id) {
            this.email_id = email_id;

        }


        public int getOtp() {
            return otp;
        }

        public void setOtp(int otp) {
            this.otp = otp;

        }
    }
}
