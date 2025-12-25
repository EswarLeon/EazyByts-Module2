package com.trading.stock_simulation.repository;

import com.trading.stock_simulation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    User findByUsername(String username);
}
