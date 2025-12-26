package com.trading.stock_simulation.controller;

import com.trading.stock_simulation.dto.BuyStockRequest;
import com.trading.stock_simulation.dto.SellStockRequest;
import com.trading.stock_simulation.entity.*;
import com.trading.stock_simulation.repository.*;
import com.trading.stock_simulation.service.SessionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trade")
public class TradingController {

    private final StockRepository stockRepository;
    private final WalletRepository walletRepository;
    private final PortfolioRepository portfolioRepository;
    private final TradeHistoryRepository tradeHistoryRepository;
    private final SessionService sessionService;

    public TradingController(
            StockRepository stockRepository,
            WalletRepository walletRepository,
            PortfolioRepository portfolioRepository,
            TradeHistoryRepository tradeHistoryRepository,
            SessionService sessionService
    ) {
        this.stockRepository = stockRepository;
        this.walletRepository = walletRepository;
        this.portfolioRepository = portfolioRepository;
        this.tradeHistoryRepository = tradeHistoryRepository;
        this.sessionService = sessionService;
    }


    @PostMapping("/buy")
    public String buyStock(@RequestBody BuyStockRequest request,
                           HttpSession session) {

        User user = sessionService.getLoggedInUser(session);
        if (user == null) return "Not logged in ❌";

        Stock stock =
                stockRepository.findBySymbol(request.getStockSymbol());
        if (stock == null) return "Stock not found ❌";

        Wallet wallet = walletRepository.findByUser(user);
        if (wallet == null) return "Wallet not found ❌";

        double totalCost =
                stock.getPrice() * request.getQuantity();

        if (wallet.getBalance() < totalCost) {
            return "Insufficient balance ❌";
        }

        wallet.setBalance(wallet.getBalance() - totalCost);
        walletRepository.save(wallet);

        Portfolio portfolio =
                portfolioRepository.findByUserAndStock(user, stock);

        if (portfolio == null) {
            portfolio = new Portfolio(
                    user,
                    stock,
                    request.getQuantity(),
                    stock.getPrice()
            );
        } else {
            portfolio.setQuantity(
                    portfolio.getQuantity() + request.getQuantity()
            );
        }

        portfolioRepository.save(portfolio);

        tradeHistoryRepository.save(
                new TradeHistory(
                        "BUY",
                        stock.getSymbol(),
                        request.getQuantity(),
                        stock.getPrice(),
                        user
                )
        );

        return "Stock bought successfully ✅";
    }

    @PostMapping("/sell")
    public String sellStock(@RequestBody SellStockRequest request,
                            HttpSession session) {

        User user = sessionService.getLoggedInUser(session);
        if (user == null) return "Not logged in ❌";

        Stock stock =
                stockRepository.findBySymbol(request.getStockSymbol());
        if (stock == null) return "Stock not found ❌";

        Portfolio portfolio =
                portfolioRepository.findByUserAndStock(user, stock);

        if (portfolio == null) {
            return "You do not own this stock ❌";
        }

        if (request.getQuantity() > portfolio.getQuantity()) {
            return "Not enough stock quantity to sell ❌";
        }

        Wallet wallet = walletRepository.findByUser(user);

        double sellAmount =
                stock.getPrice() * request.getQuantity();

        wallet.setBalance(wallet.getBalance() + sellAmount);
        walletRepository.save(wallet);

        int remainingQty =
                portfolio.getQuantity() - request.getQuantity();

        if (remainingQty == 0) {
            portfolioRepository.delete(portfolio);
        } else {
            portfolio.setQuantity(remainingQty);
            portfolioRepository.save(portfolio);
        }

        tradeHistoryRepository.save(
                new TradeHistory(
                        "SELL",
                        stock.getSymbol(),
                        request.getQuantity(),
                        stock.getPrice(),
                        user
                )
        );

        return "Stock sold successfully ✅";
    }
}
