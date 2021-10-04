package com.salveo.mysalveo.requestpojo;

public class DoctorMyCalendarAvlDaysRequest {

    /**
     * user_id : 1234567890
     * Doctor_name : mohammed6
     * types : 1
     */

    private String user_id;
    private String Doctor_name;
    private int types;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getDoctor_name() {
        return Doctor_name;
    }

    public void setDoctor_name(String Doctor_name) {
        this.Doctor_name = Doctor_name;
    }

    public int getTypes() {
        return types;
    }

    public void setTypes(int types) {
        this.types = types;
    }
}
