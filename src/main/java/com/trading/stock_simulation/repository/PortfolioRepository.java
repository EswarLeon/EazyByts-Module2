package com.trading.stock_simulation.repository;

import com.trading.stock_simulation.entity.Portfolio;
import com.trading.stock_simulation.entity.Stock;
import com.trading.stock_simulation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {

    Portfolio findByUserAndStock(User user, Stock stock);
    List<Portfolio> findByUser(User user);
}
