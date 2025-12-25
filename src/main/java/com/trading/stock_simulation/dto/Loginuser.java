package com.trading.stock_simulation.dto;


public class Loginuser {
    private String username;
    private String password;

    public Loginuser() {
    }

    public String getUsername() {
        return username;
    }

    public Loginuser(String username, String password) {
        this.username = username;
        this.password = password;
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
}
