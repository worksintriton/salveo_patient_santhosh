package com.salveo.mysalveo.requestpojo;

public class FetchByIdRequest {

    /**
     * user_id : 603e27792c2b43125f8cb802
     * product_id : 6034d6a5888af7628e7e17d4
     */

    private String user_id;
    private String product_id;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }
}
