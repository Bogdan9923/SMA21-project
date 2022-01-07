package com.app.shoop;

import android.util.Log;

public class Product {

    public String name;
    public String image;
    public int price;
    public String description;

    public Product()
    {

    }

    public Product(String name, String image, int price ) {
        this.name = name;
        this.image = image;
        this.price = price;

    }

    public Product(String name, String image, int price ,String description) {
        this.name = name;
        this.image = image;
        this.price = price;
        this.description = description;

    }


    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public int getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
