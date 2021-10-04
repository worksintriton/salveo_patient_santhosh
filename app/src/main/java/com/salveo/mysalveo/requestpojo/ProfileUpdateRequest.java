package com.salveo.mysalveo.requestpojo;

public class ProfileUpdateRequest {

    /**
     * user_id : 5fd778437aa4cc1c6a1e5632
     * first_name : Sam
     * last_name : san
     * user_email : santhoshvsk94@gmail.com
     * user_email_verification
     */

    private String user_id;
    private String first_name;
    private String last_name;
    private String user_email;
    private boolean user_email_verification;

    public boolean isUser_email_verification() {
        return user_email_verification;
    }

    public void setUser_email_verification(boolean user_email_verification) {
        this.user_email_verification = user_email_verification;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;

    }


    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;

    }


    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }


    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;

    }
}
