package com.salveo.mysalveo.requestpojo;

public class FilterDoctorRequest {

    /**
     * user_id : 5fd841a67aa4cc1c6a1e5636
     * specialization :
     * nearby : 0
     * Review_count : 5
     */

    private String user_id;
    private String specialization;
    private int nearby;
    private int Review_count;


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;

    }


    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;

    }


    public int getNearby() {
        return nearby;
    }

    public void setNearby(int nearby) {
        this.nearby = nearby;
    }


    public int getReview_count() {
        return Review_count;
    }

    public void setReview_count(int Review_count) {
        this.Review_count = Review_count;
    }
}
