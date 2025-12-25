package com.trading.stock_simulation.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "trade_history")
public class TradeHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type; // BUY or SELL

    private String stockSymbol;

    private int quantity;

    private double price;

    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public TradeHistory() {}

    public TradeHistory(String type, String stockSymbol,
                        int quantity, double price,
                        User user) {
        this.type = type;
        this.stockSymbol = stockSymbol;
        this.quantity = quantity;
        this.price = price;
        this.user = user;
        this.time = LocalDateTime.now();
    }

    // getters only (logs should not be edited)
    public String getType() { return type; }
    public String getStockSymbol() { return stockSymbol; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }
    public LocalDateTime getTime() { return time; }
}
