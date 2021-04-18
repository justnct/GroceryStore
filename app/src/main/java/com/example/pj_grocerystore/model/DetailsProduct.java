package com.example.pj_grocerystore.model;

public class DetailsProduct {
    private String name;
    private int price;
    private int amount;
    private int total;

    public DetailsProduct(String name, int price, int amount, int total) {
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public int getTotal() {
        return total;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
