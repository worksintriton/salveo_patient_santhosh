package com.salveo.mysalveo.requestpojo;

public class VendorOrderRequest {


    /**
     * user_id : 6025040ee15519672cd0dc02
     * order_deliver_status : missed
     */

    private String user_id;
    private String order_deliver_status;


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;

    }


    public String getOrder_deliver_status() {
        return order_deliver_status;
    }

    public void setOrder_deliver_status(String order_deliver_status) {
        this.order_deliver_status = order_deliver_status;

    }
}
