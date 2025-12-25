package com.trading.stock_simulation.controller;

import com.trading.stock_simulation.dto.PortfolioResponse;
import com.trading.stock_simulation.entity.User;
import com.trading.stock_simulation.service.PortfolioService;
import com.trading.stock_simulation.service.SessionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/portfolio")
public class PortfolioController {

    private final PortfolioService portfolioService;
    private final SessionService sessionService;

    public PortfolioController(PortfolioService portfolioService,
                               SessionService sessionService) {
        this.portfolioService = portfolioService;
        this.sessionService = sessionService;
    }

    @GetMapping
    public List<PortfolioResponse> getPortfolio(HttpSession session) {

        User user = sessionService.getLoggedInUser(session);

        if (user == null) {
            return List.of();
        }

        return portfolioService.getPortfolio(user);
    }
}
