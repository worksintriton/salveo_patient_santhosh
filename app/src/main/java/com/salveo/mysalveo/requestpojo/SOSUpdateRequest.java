package com.salveo.mysalveo.requestpojo;

public class SOSUpdateRequest {

    /**
     * user_id : 60e466f0e87f127291325203
     * id : 3
     * name : Imthi
     * phone : 98989989898
     */

    private String user_id;
    private int id;
    private String name;
    private String phone;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
