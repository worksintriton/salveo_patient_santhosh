package com.salveo.mysalveo.requestpojo;

import java.util.List;

public class DoctorMyCalendarUpdateDocDateRequest {

    /**
     * user_id : 123123131313123123123123
     * days : ["Monday","Tuesday","Wednesday"]
     * timing : [{"Time":"01:00 AM","Status":true},{"Time":"02:00 AM","Status":true},{"Time":"03:00 AM","Status":true},{"Time":"04:00 AM","Status":true}]
     */

    private String user_id;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    private List<String> days;


    private List<TimingBean> timing;


    public List<String> getDays() {
        return days;
    }

    public void setDays(List<String> days) {
        this.days = days;
    }

    public List<TimingBean> getTiming() {
        return timing;
    }

    public void setTiming(List<TimingBean> timing) {
        this.timing = timing;
    }

    public static class TimingBean {
        /**
         * Time : 01:00 AM
         * Status : true
         * format
         */

        private String Time;

        private String format;

        public String getFormat() {
            return format;
        }

        public void setFormat(String format) {
            this.format = format;
        }

        private boolean Status;

        public String getTime() {
            return Time;
        }

        public void setTime(String Time) {
            this.Time = Time;
        }

        public boolean isStatus() {
            return Status;
        }

        public void setStatus(boolean Status) {
            this.Status = Status;
        }
    }
}
