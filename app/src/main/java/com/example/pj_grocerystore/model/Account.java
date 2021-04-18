package com.example.pj_grocerystore.model;

import java.util.ArrayList;

public class Account {
    private String username;
    private String password;
    private String email;
    private int isActive;
    private int level;

    public Account(String username, String password, String email, int isActive, int level) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.isActive = isActive;
        this.level = level;
    }

    public Account(){
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

}
