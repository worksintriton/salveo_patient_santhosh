package com.salveo.mysalveo.requestpojo;

public class PetLoverCancelSingleOrderRequest {

    /**
     * order_id : ORDER-1618982982287
     * product_id : 0
     * date : 20-04-2021 12:47 PM
     * reject_reason
     */

    private String order_id;
    private int product_id;
    private String date;
    private String reject_reason;

    public String getReject_reason() {
        return reject_reason;
    }

    public void setReject_reason(String reject_reason) {
        this.reject_reason = reject_reason;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
