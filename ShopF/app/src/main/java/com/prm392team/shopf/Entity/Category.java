package com.prm392team.shopf.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "category")
public class Category implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int categoryId;
    private String categoryName;
    private String image;

    public Category() {
    }

    public Category(int categoryId, String categoryName, String image) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.image = image;
    }

    public Category(String categoryName, String image) {
        this.categoryName = categoryName;
        this.image = image;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryName='" + categoryName + '\'' +
                ", Image='" + image + '\'' +
                '}';
    }
}
