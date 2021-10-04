package com.salveo.mysalveo.requestpojo;

public class ProductFiltersRequest {

    /**
     * pet_type : 602d1bf4562e0916bc9b3215
     * pet_breed :
     * discount_value :
     * cat_id :
     */

    private String pet_type;
    private String pet_breed;
    private String discount_value;
    private String cat_id;

    public String getPet_type() {
        return pet_type;
    }

    public void setPet_type(String pet_type) {
        this.pet_type = pet_type;
    }

    public String getPet_breed() {
        return pet_breed;
    }

    public void setPet_breed(String pet_breed) {
        this.pet_breed = pet_breed;
    }

    public String getDiscount_value() {
        return discount_value;
    }

    public void setDiscount_value(String discount_value) {
        this.discount_value = discount_value;
    }

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }
}
