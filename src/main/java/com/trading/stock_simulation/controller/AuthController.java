package com.trading.stock_simulation.controller;

import com.trading.stock_simulation.entity.User;
import com.trading.stock_simulation.service.SessionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final SessionService sessionService;

    public AuthController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    // ✅ CHECK IF USER IS LOGGED IN
    @GetMapping("/check")
    public boolean checkLogin(HttpSession session) {

        User user = sessionService.getLoggedInUser(session);
        return user != null;
    }
}
