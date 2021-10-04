package com.salveo.mysalveo.requestpojo;

public class PetLoverVendorOrderListRequest {

    /**
     * petlover_id : 603e27792c2b43125f8cb802
     * status : New
     * skip_count : 1
     */

    private String petlover_id;
    private String status;
    private int skip_count;

    public String getPetlover_id() {
        return petlover_id;
    }

    public void setPetlover_id(String petlover_id) {
        this.petlover_id = petlover_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public int getSkip_count() {
        return skip_count;
    }

    public void setSkip_count(int skip_count) {
        this.skip_count = skip_count;
    }
}
