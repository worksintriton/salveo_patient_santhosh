package com.salveo.mysalveo.requestpojo;

import java.util.List;

public class VendorOrderUpdateDispatchRequest {

    /**
     * order_id : ORDER-1618919599393
     * product_id : [0]
     * date : 20-04-2021 12:47 PM
     * track_id : 1234 tracid
     * vendor_id
     */

    private String order_id;
    private String date;
    private String track_id;
    private List<Integer> product_id;
    private String vendor_id;

    public String getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(String vendor_id) {
        this.vendor_id = vendor_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTrack_id() {
        return track_id;
    }

    public void setTrack_id(String track_id) {
        this.track_id = track_id;
    }

    public List<Integer> getProduct_id() {
        return product_id;
    }

    public void setProduct_id(List<Integer> product_id) {
        this.product_id = product_id;
    }
}
