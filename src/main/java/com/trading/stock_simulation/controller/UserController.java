package com.trading.stock_simulation.controller;

import com.trading.stock_simulation.dto.Loginuser;
import com.trading.stock_simulation.dto.RegisterRequest;
import com.trading.stock_simulation.entity.User;
import com.trading.stock_simulation.entity.Wallet;
import com.trading.stock_simulation.repository.UserRepository;
import com.trading.stock_simulation.repository.WalletRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final WalletRepository walletRepository;

    public UserController(UserRepository userRepository,
                          PasswordEncoder passwordEncoder,
                          WalletRepository walletRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.walletRepository = walletRepository;
    }

    // ✅ REGISTER
    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail()))
            return "Email already registered ❌";

        if (userRepository.existsByUsername(request.getUsername()))
            return "Username already exists ❌";

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);

        Wallet wallet = new Wallet();
        wallet.setUser(user);
        wallet.setBalance(100000.0);
        walletRepository.save(wallet);

        return "User registered & wallet credited ₹1,00,000 ✅";
    }

    // ✅ LOGIN (NO INVALIDATE HERE)
    @PostMapping("/login")
    public String login(@RequestBody Loginuser loginuser,
                        HttpSession session) {

        User user = userRepository.findByUsername(loginuser.getUsername());

        if (user == null)
            return "Invalid Username ❌";

        if (!passwordEncoder.matches(
                loginuser.getPassword(),
                user.getPassword()))
            return "Invalid Password ❌";

        session.setAttribute("LOGGED_IN_USER", user.getUsername());

        return "Login successful ✅";
    }

    // ✅ LOGOUT
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "Logged out successfully ✅";
    }
}
