package com.rishav.urlshortener.services;

import com.rishav.urlshortener.models.UserModel;
import com.rishav.urlshortener.repository.UserRepo;
import com.rishav.urlshortener.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public String register(String username, String password){
        UserModel user = UserModel.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .build();
        userRepo.save(user);
        return "User registered successfully";
    }

    public String login(String username, String password) {
        UserModel user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (passwordEncoder.matches(password, user.getPassword())) {
            return jwtUtil.generateToken(username);
        }
        throw new RuntimeException("Invalid credentials");
    }
}
