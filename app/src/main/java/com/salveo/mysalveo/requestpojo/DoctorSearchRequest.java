package com.salveo.mysalveo.requestpojo;

public class DoctorSearchRequest {


    /**
     * search_string :
     * communication_type : 0
     * user_id : 5fd227ac80791a71361baad3
     */

    private String search_string;
    private int communication_type;
    private String user_id;


    public String getSearch_string() {
        return search_string;
    }

    public void setSearch_string(String search_string) {
        this.search_string = search_string;
    }


    public int getCommunication_type() {
        return communication_type;
    }

    public void setCommunication_type(int communication_type) {
        this.communication_type = communication_type;

    }


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;

    }
}
