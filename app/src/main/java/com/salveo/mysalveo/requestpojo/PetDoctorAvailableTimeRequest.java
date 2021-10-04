package com.salveo.mysalveo.requestpojo;

public class PetDoctorAvailableTimeRequest {

    /**
     * Date : 31-11-2020
     * user_id : 1234567890
     * cur_date : 31-11-2020
     * cur_time : 01:00 AM
     * current_time
     */

    private String Date;
    private String user_id;
    private String cur_date;
    private String cur_time;
    private String current_time;

    public String getCurrent_time() {
        return current_time;
    }

    public void setCurrent_time(String current_time) {
        this.current_time = current_time;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;

    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;

    }


    public String getCur_date() {
        return cur_date;
    }

    public void setCur_date(String cur_date) {
        this.cur_date = cur_date;

    }

    public String getCur_time() {
        return cur_time;
    }

    public void setCur_time(String cur_time) {
        this.cur_time = cur_time;

    }
}
