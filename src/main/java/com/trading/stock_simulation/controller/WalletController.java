package com.trading.stock_simulation.controller;

import com.trading.stock_simulation.entity.User;
import com.trading.stock_simulation.entity.Wallet;
import com.trading.stock_simulation.repository.WalletRepository;
import com.trading.stock_simulation.service.SessionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {

    private final WalletRepository walletRepository;
    private final SessionService sessionService;

    public WalletController(WalletRepository walletRepository,
                            SessionService sessionService) {
        this.walletRepository = walletRepository;
        this.sessionService = sessionService;
    }

    @GetMapping("/balance")
    public String getBalance(HttpSession session) {

        User user = sessionService.getLoggedInUser(session);
        if (user == null) {
            return "Not logged in ❌";
        }

        Wallet wallet = walletRepository.findByUser(user);
        if (wallet == null) {
            return "Wallet not found ❌";
        }

        return "Your wallet balance is ₹" + wallet.getBalance();
    }
}
