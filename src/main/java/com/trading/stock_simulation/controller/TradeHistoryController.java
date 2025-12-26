package com.trading.stock_simulation.controller;

import com.trading.stock_simulation.entity.TradeHistory;
import com.trading.stock_simulation.entity.User;
import com.trading.stock_simulation.repository.TradeHistoryRepository;
import com.trading.stock_simulation.service.SessionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/history")
public class TradeHistoryController {

    private final TradeHistoryRepository tradeHistoryRepository;
    private final SessionService sessionService;

    public TradeHistoryController(
            TradeHistoryRepository tradeHistoryRepository,
            SessionService sessionService) {
        this.tradeHistoryRepository = tradeHistoryRepository;
        this.sessionService = sessionService;
    }

    @GetMapping
    public List<TradeHistory> getHistory(HttpSession session) {

        User user = sessionService.getLoggedInUser(session);

        if (user == null) {
            return List.of();
        }

        return tradeHistoryRepository
                .findByUserOrderByTimeDesc(user);
    }
}
