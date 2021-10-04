package com.salveo.mysalveo.requestpojo;


import com.google.gson.annotations.SerializedName;

public class PetLoverDashboardRequest {

    /**
     * Doctor : 0
     * Product : 0
     * address : Unnamed Road, Tamil Nadu 621006, India
     * lat : 11.055715
     * long : 78.632249
     * service : 0
     * user_id : 5fd227ac80791a71361baad3
     * user_type : 1
     */

    private int Doctor;
    private int Product;
    private String address;
    private double lat;
    @SerializedName("long")
    private double longX;
    private int service;
    private String user_id;
    private int user_type;

    public int getDoctor() {
        return Doctor;
    }

    public void setDoctor(int Doctor) {
        this.Doctor = Doctor;
    }


    public int getProduct() {
        return Product;
    }

    public void setProduct(int Product) {
        this.Product = Product;

    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;

    }


    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;

    }

    public double getLongX() {
        return longX;
    }

    public void setLongX(double longX) {
        this.longX = longX;

    }


    public int getService() {
        return service;
    }

    public void setService(int service) {
        this.service = service;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }
}
