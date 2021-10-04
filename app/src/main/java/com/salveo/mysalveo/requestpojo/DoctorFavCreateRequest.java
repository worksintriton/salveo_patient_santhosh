package com.salveo.mysalveo.requestpojo;

public class DoctorFavCreateRequest {

    /**
     * user_id : 603e262e2c2b43125f8cb801
     * doctor_id : 603e31a02c2b43125f8cb806
     */

    private String user_id;
    private String doctor_id;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
    }
}
