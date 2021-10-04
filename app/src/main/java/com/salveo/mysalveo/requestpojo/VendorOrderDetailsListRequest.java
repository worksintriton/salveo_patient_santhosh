package com.salveo.mysalveo.requestpojo;

public class VendorOrderDetailsListRequest {

    /**
     *  vendor_id;
     * order_id : ORDER-1618894598446
     */

    private String order_id;
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


}
