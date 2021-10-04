package com.salveo.mysalveo.requestpojo;

public class ProductSortByRequest {

    /**
     * recent : 0
     * high_discount : 0
     * best_sellers : 0
     * high_to_low : 0
     * low_to_high : 1
     * cat_id :
     * today_deals : true
     */

    private int recent;
    private int high_discount;
    private int best_sellers;
    private int high_to_low;
    private int low_to_high;
    private String cat_id;
    private boolean today_deals;


    public int getRecent() {
        return recent;
    }

    public void setRecent(int recent) {
        this.recent = recent;

    }


    public int getHigh_discount() {
        return high_discount;
    }

    public void setHigh_discount(int high_discount) {
        this.high_discount = high_discount;

    }


    public int getBest_sellers() {
        return best_sellers;
    }

    public void setBest_sellers(int best_sellers) {
        this.best_sellers = best_sellers;

    }


    public int getHigh_to_low() {
        return high_to_low;
    }

    public void setHigh_to_low(int high_to_low) {
        this.high_to_low = high_to_low;

    }


    public int getLow_to_high() {
        return low_to_high;
    }

    public void setLow_to_high(int low_to_high) {
        this.low_to_high = low_to_high;

    }


    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;

    }


    public boolean isToday_deals() {
        return today_deals;
    }

    public void setToday_deals(boolean today_deals) {
        this.today_deals = today_deals;

    }
}
