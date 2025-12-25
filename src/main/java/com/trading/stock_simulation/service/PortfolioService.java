package com.trading.stock_simulation.service;

import com.trading.stock_simulation.dto.PortfolioResponse;
import com.trading.stock_simulation.entity.Portfolio;
import com.trading.stock_simulation.entity.User;
import com.trading.stock_simulation.repository.PortfolioRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;

    public PortfolioService(PortfolioRepository portfolioRepository) {
        this.portfolioRepository = portfolioRepository;
    }

    public List<PortfolioResponse> getPortfolio(User user) {

        List<Portfolio> portfolios =
                portfolioRepository.findByUser(user);

        List<PortfolioResponse> responseList = new ArrayList<>();

        for (Portfolio p : portfolios) {

            double currentPrice = p.getStock().getPrice();
            double buyPrice = p.getBuyPrice();
            int quantity = p.getQuantity();

            double profitOrLoss =
                    (currentPrice - buyPrice) * quantity;

            responseList.add(
                    new PortfolioResponse(
                            p.getStock().getSymbol(),
                            p.getStock().getName(),
                            quantity,
                            buyPrice,
                            currentPrice,
                            profitOrLoss
                    )
            );
        }

        return responseList;
    }
}
