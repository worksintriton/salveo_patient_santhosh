package com.salveo.mysalveo.requestpojo;

public class CartAddProductRequest {

    /**
     * user_id : 603e27792c2b43125f8cb802
     * product_id : 602e4940f62e8d2089fba978
     * count : 3
     */

    private String user_id;
    private String product_id;
    private int count;

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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
