package com.salveo.mysalveo.requestpojo;

public class CreateHolidayRequest {


    /**
     * user_id : 1234567890
     * Date : 23-10-2020
     */

    private String user_id;
    private String Date;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }
}
