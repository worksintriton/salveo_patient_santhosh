package com.salveo.mysalveo.interfaces;

public interface OnItemCheckProduct {
    void onItemCheckProduct(int count,String product_id, String product_name,int product_price);
    void onItemUnCheckProduct(int count,String product_id, String product_name,int product_price);
}
