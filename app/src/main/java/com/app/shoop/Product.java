package com.app.shoop;

import android.util.Log;

public class Product {

    private static final String TAG = "ProductPage";

    public String name;
    public String image;
    public int price;


    public Product(String name, String image, int price ) {
        this.name = name;
        this.image = image;
        this.price = price;
        Log.v(TAG, "called constructor");
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


}
