package com.salveo.mysalveo.requestpojo;

public class DoctorMyCalendarAvlTimesRequest {


    /**
     * user_id : 1234567890
     * Day : Monday
     */

    private String user_id;
    private String Day;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getDay() {
        return Day;
    }

    public void setDay(String Day) {
        this.Day = Day;
    }
}
