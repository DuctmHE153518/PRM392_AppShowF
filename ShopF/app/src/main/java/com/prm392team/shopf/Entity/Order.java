package com.prm392team.shopf.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "order")
public class Order {
    @PrimaryKey(autoGenerate = true)
    private int orderId;
    private int productId;
    private int userId;
    private String orderDate;
    private double total;
    private String orderAddress;
    private int status;

    public Order() {
    }

    public Order(int orderId, int productId, int userId, String orderDate, double total, String orderAddress, int status) {
        this.orderId = orderId;
        this.productId = productId;
        this.userId = userId;
        this.orderDate = orderDate;
        this.total = total;
        this.orderAddress = orderAddress;
        this.status = status;
    }

    public Order(int productId, int userId, String orderDate, double total, String orderAddress, int status) {
        this.productId = productId;
        this.userId = userId;
        this.orderDate = orderDate;
        this.total = total;
        this.orderAddress = orderAddress;
        this.status = status;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
