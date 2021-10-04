package com.salveo.mysalveo.requestpojo;

public class PetVendorOrderRequest {

    /**
     * user_id : 603e27792c2b43125f8cb802
     * order_status : New
     */

    private String user_id;
    private String order_status;


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;

    }


    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;

    }
}
