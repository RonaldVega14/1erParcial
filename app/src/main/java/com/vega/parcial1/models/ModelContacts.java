package com.vega.parcial1.models;

public class ModelContacts {

    private String name, number;
    private boolean fav;

    public ModelContacts(String name, String number, boolean fav) {
        this.name = name;
        this.number = number;
        this.fav = fav;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean isFav() {
        return fav;
    }

    public void setFav(boolean fav) {
        this.fav = fav;
    }
}
