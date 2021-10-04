package com.salveo.mysalveo.responsepojo;

public class AddSpecTypeListModel {

    public int pos;
    public String specname;

    public AddSpecTypeListModel(int pos, String specname) {

        this.pos = pos;
        this.specname = specname;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public String getSpecname() {
        return specname;
    }

    public void setSpecname(String specname) {
        this.specname = specname;
    }

    @Override
    public String toString() {
        return "AddSpecTypeListModel{" +
                "pos=" + pos +
                ", specname='" + specname + '\'' +
                '}';
    }
}
