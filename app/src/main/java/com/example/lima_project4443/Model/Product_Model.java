package com.example.lima_project4443.Model;

public class Product_Model {
    private String productName;

    private int imageID;
    private float price;
    private float rating;
    private String color;
    private long productId;
    private String brand;
    public Product_Model(){

    }

    public Product_Model(String productName, int price, int imageID) {
        this.productName = productName;
        this.price = price;
        this.imageID = imageID;
    }


    public void setProductId(long productId) {
        this.productId = productId;
    }
    public void setProductName(String name){
        this.productName = name;
    }

    public void setPrice(float price){
        this.price = price;
    }
    public void setRating(float rating){
        this.rating = rating;
    }

    public void setColor(String color) {
        this.color = color;
    }
    public void setBrand(String brand){
        this.brand = brand;
    }

    public long getProductId(){
        return this.productId;
    }
    public String getProductName(){
        return this.productName;
    }

    public int getImageID() {
        return this.imageID;
    }

    public float getPrice(){
        return this.price;
    }
    public float getRating(){
        return this.rating;
    }
    public String getColor(){
        return this.color;
    }
    public String getBrand(){return this.brand;}
}
