package com.trading.stock_simulation.repository;

import com.trading.stock_simulation.entity.TradeHistory;
import com.trading.stock_simulation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TradeHistoryRepository
        extends JpaRepository<TradeHistory, Long> {

    List<TradeHistory> findByUserOrderByTimeDesc(User user);
}
