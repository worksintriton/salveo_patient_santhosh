package com.salveo.mysalveo.responsepojo;

public class AddPetTypeListModel {

    public int pos;
    public String petValue;

    public AddPetTypeListModel(int pos, String petValue) {
        this.pos = pos;
        this.petValue = petValue;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public String getPetValue() {
        return petValue;
    }

    public void setPetValue(String petValue) {
        this.petValue = petValue;
    }
}
