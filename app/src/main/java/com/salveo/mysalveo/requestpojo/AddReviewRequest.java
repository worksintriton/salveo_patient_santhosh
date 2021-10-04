package com.salveo.mysalveo.requestpojo;

public class AddReviewRequest {

    /**
     * _id : 5fd30a701978e618628c966c
     * user_feedback :
     * user_rate : 0
     */

    private String _id;
    private String user_feedback;
    private String user_rate;


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;

    }


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
