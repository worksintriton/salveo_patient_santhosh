package com.salveo.mysalveo.requestpojo;

public class TrackOrderDetailsRequest {

    /**
     * order_id : ORDER-1618912722227
     * product_order_id : 0
     */

    private String order_id;
    private int product_order_id;

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public int getProduct_order_id() {
        return product_order_id;
    }

    public void setProduct_order_id(int product_order_id) {
        this.product_order_id = product_order_id;
    }
}
