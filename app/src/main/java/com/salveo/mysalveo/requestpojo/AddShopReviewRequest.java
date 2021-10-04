package com.salveo.mysalveo.requestpojo;

public class AddShopReviewRequest {

    /**
     * order_id : 5fd30a701978e618628c966c
     * user_feedback :
     * user_rate : 0
     */

    private String order_id;

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    private String user_feedback;
    private String user_rate;





    public String getUser_feedback() {
        return user_feedback;
    }

    public void setUser_feedback(String user_feedback) {
        this.user_feedback = user_feedback;

    }


    public String getUser_rate() {
        return user_rate;
    }

    public void setUser_rate(String user_rate) {
        this.user_rate = user_rate;

    }
}
