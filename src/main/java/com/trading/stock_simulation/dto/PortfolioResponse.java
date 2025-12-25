package com.trading.stock_simulation.dto;

public class PortfolioResponse {

    private String stockSymbol;
    private String stockName;
    private int quantity;
    private double buyPrice;
    private double currentPrice;
    private double profitOrLoss;

    public PortfolioResponse(String stockSymbol,
                             String stockName,
                             int quantity,
                             double buyPrice,
                             double currentPrice,
                             double profitOrLoss) {
        this.stockSymbol = stockSymbol;
        this.stockName = stockName;
        this.quantity = quantity;
        this.buyPrice = buyPrice;
        this.currentPrice = currentPrice;
        this.profitOrLoss = profitOrLoss;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public String getStockName() {
        return stockName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public double getProfitOrLoss() {
        return profitOrLoss;
    }
}
