package com.salveo.mysalveo.requestpojo;

public class SPSpecificServiceDetailsRequest {

    /**
     * cata_id : 5fe185d61996f651f5133693
     * distance : 0
     * user_id : 5ffe70d5b699b42563933d90
     * Count_value_start : 0
     * Count_value_end : 500
     * review_count : 3
     */

    private String cata_id;
    private int distance;
    private String user_id;
    private int Count_value_start;
    private int Count_value_end;
    private int review_count;


    public String getCata_id() {
        return cata_id;
    }

    public void setCata_id(String cata_id) {
        this.cata_id = cata_id;

    }


    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;

    }


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;

    }


    public int getCount_value_start() {
        return Count_value_start;
    }

    public void setCount_value_start(int Count_value_start) {
        this.Count_value_start = Count_value_start;

    }


    public int getCount_value_end() {
        return Count_value_end;
    }

    public void setCount_value_end(int Count_value_end) {
        this.Count_value_end = Count_value_end;
    }


    public int getReview_count() {
        return review_count;
    }

    public void setReview_count(int review_count) {
        this.review_count = review_count;
    }
}
