package com.prm392team.shopf.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class User {
    @PrimaryKey(autoGenerate = true)
    private int userId;
    private String email;
    private String username;
    private String password;
    private String phone;
    private String address;
    private int role;
    private String dob;

    public User() {
    }

    public User(String email, String username, String password, String phone, String address, int role, String dob) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.role = role;
        this.dob = dob;
    }

    public User(int userId, String email, String username, String password, String phone, String address, int role, String dob) {
        this.userId = userId;
        this.email = email;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.role = role;
        this.dob = dob;
    }



    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
