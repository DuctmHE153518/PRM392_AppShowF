package com.prm392team.shopf.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "product")
public class Product implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int productId;
    private String productName;
    private int quantity;
    private String description;
    private double price;
    private double discount;
    private String image;
    private int categoryIds;
    private int sellerIds;
    private int sold;

    public Product() {
    }

    public Product(String productName, String description, double price, String image) {
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.image = image;
    }

    public Product(int productId, String productName, int quantity, String description, double price, double discount, String image, int categoryIds, int sellerIds, int sold) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.description = description;
        this.price = price;
        this.discount = discount;
        this.image = image;
        this.categoryIds = categoryIds;
        this.sellerIds = sellerIds;
        this.sold = sold;
    }

    public Product(String productName, int quantity, String description, double price, double discount, String image, int categoryIds, int sellerIds, int sold) {
        this.productName = productName;
        this.quantity = quantity;
        this.description = description;
        this.price = price;
        this.discount = discount;
        this.image = image;
        this.categoryIds = categoryIds;
        this.sellerIds = sellerIds;
        this.sold = sold;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(int categoryIds) {
        this.categoryIds = categoryIds;
    }

    public int getSellerIds() {
        return sellerIds;
    }

    public void setSellerIds(int sellerIds) {
        this.sellerIds = sellerIds;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }
}
