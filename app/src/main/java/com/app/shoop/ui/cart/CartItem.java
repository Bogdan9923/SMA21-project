package com.app.shoop.ui.cart;

public class CartItem {
    private String name;
    private int price;
    private int count;

    public CartItem(String name, int price, int count)
    {
        this.count = count;
        this.name = name;
        this.price = price;
    }

    public CartItem()
    {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
