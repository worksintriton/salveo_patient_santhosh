package com.salveo.mysalveo.responsepojo;

import java.util.List;

public class SPAvailableTimeResponse {

    /**
     * Status : Success
     * Message : Service Provider Available
     * Data : [{"Comm_type_chat":"No","Comm_type_video":"No","sp_email_id":"","sp_ava_Date":"12-01-2021","sp_name":"","Times":[{"time":"12:00 AM"},{"time":"01:15 AM"},{"time":"01:45 AM"},{"time":"02:45 AM"},{"time":"03:15 AM"},{"time":"05:15 AM"},{"time":"05:45 AM"},{"time":"06:00 AM"},{"time":"06:15 AM"}]}]
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

    public static class DataBean  {
        /**
         * Comm_type_chat : No
         * Comm_type_video : No
         * sp_email_id :
         * sp_ava_Date : 12-01-2021
         * sp_name :
         * Times : [{"time":"12:00 AM"},{"time":"01:15 AM"},{"time":"01:45 AM"},{"time":"02:45 AM"},{"time":"03:15 AM"},{"time":"05:15 AM"},{"time":"05:45 AM"},{"time":"06:00 AM"},{"time":"06:15 AM"}]
         */

        private String Comm_type_chat;
        private String Comm_type_video;
        private String sp_email_id;
        private String sp_ava_Date;
        private String sp_name;
        private List<TimesBean> Times;


        public String getComm_type_chat() {
            return Comm_type_chat;
        }

        public void setComm_type_chat(String Comm_type_chat) {
            this.Comm_type_chat = Comm_type_chat;

        }


        public String getComm_type_video() {
            return Comm_type_video;
        }

        public void setComm_type_video(String Comm_type_video) {
            this.Comm_type_video = Comm_type_video;

        }


        public String getSp_email_id() {
            return sp_email_id;
        }

        public void setSp_email_id(String sp_email_id) {
            this.sp_email_id = sp_email_id;

        }

        public String getSp_ava_Date() {
            return sp_ava_Date;
        }

        public void setSp_ava_Date(String sp_ava_Date) {
            this.sp_ava_Date = sp_ava_Date;

        }


        public String getSp_name() {
            return sp_name;
        }

        public void setSp_name(String sp_name) {
            this.sp_name = sp_name;

        }


        public List<TimesBean> getTimes() {
            return Times;
        }

        public void setTimes(List<TimesBean> Times) {
            this.Times = Times;

        }

        public static class TimesBean  {
            /**
             * time : 12:00 AM
             */

            private String time;


            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;

            }
        }
    }
}
