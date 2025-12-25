package com.trading.stock_simulation.service;

import com.trading.stock_simulation.entity.Stock;
import com.trading.stock_simulation.repository.StockRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class StockPriceSimulationService {

    private final StockRepository stockRepository;
    private final Random random = new Random();

    public StockPriceSimulationService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    // Runs every 10 seconds
    @Scheduled(fixedRate = 10000)
    public void updateStockPrices() {

        List<Stock> stocks = stockRepository.findAll();

        for (Stock stock : stocks) {

            double oldPrice = stock.getPrice();

            // Change between -5% to +5%
            double percentageChange = (random.nextDouble() * 10) - 5;

            double newPrice =
                    oldPrice + (oldPrice * percentageChange / 100);

            // Minimum price protection
            if (newPrice < 10) {
                newPrice = 10;
            }

            stock.setPrice(Math.round(newPrice * 100.0) / 100.0);
            stockRepository.save(stock);

            System.out.println(
                    stock.getSymbol() + " : " +
                            oldPrice + " → " + stock.getPrice()
            );
        }
    }
}
