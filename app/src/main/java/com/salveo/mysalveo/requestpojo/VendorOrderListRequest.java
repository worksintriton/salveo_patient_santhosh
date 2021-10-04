package com.salveo.mysalveo.requestpojo;

public class VendorOrderListRequest {

    /**
     * vendor_id : 604866a50b3a487571a1c568
     * order_status : New
     * skip_count : 1
     */

    private String vendor_id;
    private String order_status;
    private int skip_count;

    public String getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(String vendor_id) {
        this.vendor_id = vendor_id;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public int getSkip_count() {
        return skip_count;
    }

    public void setSkip_count(int skip_count) {
        this.skip_count = skip_count;
    }
}
