package com.salveo.mysalveo.responsepojo;

public class SPCheckStatusResponse {

    /**
     * Status : Success
     * Message : Doctor Status
     * Data : {"user_id":"5fc761dab8b7931efc42592f","profile_status":true,"profile_verification_status":"verified"}
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
         * user_id : 5fc761dab8b7931efc42592f
         * profile_status : true
         * profile_verification_status : verified
         * calender_status
         */

        private String user_id;
        private boolean profile_status;
        private String profile_verification_status;

        private boolean calender_status;

        public boolean isCalender_status() {
            return calender_status;
        }

        public void setCalender_status(boolean calender_status) {
            this.calender_status = calender_status;
        }


        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }


        public boolean isProfile_status() {
            return profile_status;
        }

        public void setProfile_status(boolean profile_status) {
            this.profile_status = profile_status;
        }


        public String getProfile_verification_status() {
            return profile_verification_status;
        }

        public void setProfile_verification_status(String profile_verification_status) {
            this.profile_verification_status = profile_verification_status;
        }
    }
}
