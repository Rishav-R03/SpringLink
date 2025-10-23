package com.rishav.urlshortener.controller;

import com.rishav.urlshortener.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String,String> req){
        return ResponseEntity.ok(authService.register(req.get("username"),req.get("password")));
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String,String> req){
        String token = authService.login(req.get("username"),req.get("password"));
        return ResponseEntity.ok(Map.of("token",token));
    }
}
