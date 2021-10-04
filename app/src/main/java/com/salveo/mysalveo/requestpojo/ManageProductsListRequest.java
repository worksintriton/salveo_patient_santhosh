package com.salveo.mysalveo.requestpojo;

public class ManageProductsListRequest {


    /**
     * vendor_id : 6048589d0b3a487571a1c567
     * search_string : CAT
     */

    private String vendor_id;
    private String search_string;

    public String getSearch_string() {
        return search_string;
    }

    public void setSearch_string(String search_string) {
        this.search_string = search_string;
    }

    public String getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(String vendor_id) {
        this.vendor_id = vendor_id;
    }
}
