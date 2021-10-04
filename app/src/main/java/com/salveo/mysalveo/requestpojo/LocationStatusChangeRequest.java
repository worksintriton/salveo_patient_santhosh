package com.salveo.mysalveo.requestpojo;

public class LocationStatusChangeRequest {

    /**
     * _id : 5fcf2d2a57c8326d894e4d7e
     * user_id : 5fc61b82b750da703e48da78
     * default_status : true
     */

    private String _id;
    private String user_id;
    private boolean default_status;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public boolean isDefault_status() {
        return default_status;
    }

    public void setDefault_status(boolean default_status) {
        this.default_status = default_status;
    }
}
