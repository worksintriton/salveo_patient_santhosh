package com.salveo.mysalveo.requestpojo;

public class UserStatusUpdateRequest {

    /**
     * user_id : 5fb6162a211fce241eaf53a9
     * user_status : complete
     */

    private String user_id;
    private String user_status;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_status() {
        return user_status;
    }

    public void setUser_status(String user_status) {
        this.user_status = user_status;
    }
}
