package com.salveo.mysalveo.requestpojo;

public class FetctProductByCatRequest {
    /**
     * vendor_id
     * cat_id : 5fec14a5ea832e2e73c1fc79
     * skip_count : 1
     */

    private String vendor_id;

    public String getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(String vendor_id) {
        this.vendor_id = vendor_id;
    }

    private String cat_id;
    private int skip_count;



    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;

    }


    public int getSkip_count() {
        return skip_count;
    }

    public void setSkip_count(int skip_count) {
        this.skip_count = skip_count;

    }
}
