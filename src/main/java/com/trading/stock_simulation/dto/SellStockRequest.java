package com.trading.stock_simulation.dto;

public class SellStockRequest {

    private String username;
    private String stockSymbol;
    private int quantity;

    public String getUsername() {
        return username;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public int getQuantity() {
        return quantity;
    }
}
