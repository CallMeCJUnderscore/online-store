package com.pluralsight;

public class Product {


    /*---------------VARIABLES---------------*/

    public String sku = "";
    public String name = "";
    public float price = 0.0f;

    public int quantity = 0;

    /*------------GETTERS/SETTERS------------*/

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /*--------------CONSTRUCTORS-------------*/

    public Product(String sku, String name, float price) {
        this.sku = sku;
        this.name = name;
        this.price = price;
        this.quantity = 10;
    }

    public Product(String sku, String name, float price, int quantity) {
        this.sku = sku;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Product() {

    }


    /*---------------FUNCTIONS---------------*/

    @Override
    public String toString() {
        return "Product{" +
                "sku='" + sku + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
