package com.app.shoop.ui.order;

public class Order {
    private String name;
    private String address;
    private String phoneNumber;
    private int price;
    private String items;
    private String status;
    private String email;

    public Order(String name, String address, String phoneNumber, String items, int price, String email)
    {
        this.address = address;
        this.name = name;
        this.items = items;
        this.phoneNumber = phoneNumber;
        this.price = price;
        this.email = email;
        this.status = "Order placed";
    }

    public Order()
    {

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
