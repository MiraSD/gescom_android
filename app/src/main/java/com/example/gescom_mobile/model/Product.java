package com.example.gescom_mobile.model;

public class Product {

    public Integer prid;
    public String name;

    public Product(Integer prid, String name) {
        this.prid = prid;
        this.name = name;
    }

    public Integer getPrid() {
        return prid;
    }

    public void setPrid(Integer prid) {
        this.prid = prid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
