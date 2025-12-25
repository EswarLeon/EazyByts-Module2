package com.trading.stock_simulation.service;

import com.trading.stock_simulation.entity.User;
import com.trading.stock_simulation.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    private final UserRepository userRepository;

    public SessionService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getLoggedInUser(HttpSession session) {

        String username =
                (String) session.getAttribute("LOGGED_IN_USER");

        if (username == null) {
            return null;
        }

        return userRepository.findByUsername(username);
    }
}
