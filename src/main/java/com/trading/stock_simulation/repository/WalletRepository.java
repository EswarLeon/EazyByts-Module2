package com.trading.stock_simulation.repository;

import com.trading.stock_simulation.entity.User;
import com.trading.stock_simulation.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet,Long> {
    Wallet findByUser(User user);
}
