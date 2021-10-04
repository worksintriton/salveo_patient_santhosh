package com.salveo.mysalveo.requestpojo;

public class ShippingAddrMarkAsLastUsedRequest {


    /**
     * _id : 6058f4ebe748565ddb1fc515
     * user_id : 604081d12c2b43125f8cb840
     * user_address_stauts : Last Used
     */

    private String _id;
    private String user_id;
    private String user_address_stauts;

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

    public String getUser_address_stauts() {
        return user_address_stauts;
    }

    public void setUser_address_stauts(String user_address_stauts) {
        this.user_address_stauts = user_address_stauts;
    }
}
