package com.rishav.urlshortener.services;

import com.rishav.urlshortener.DTO.UrlRequest;
import com.rishav.urlshortener.DTO.UrlResponse;
import com.rishav.urlshortener.models.UrlModel;
import com.rishav.urlshortener.models.UserModel;
import com.rishav.urlshortener.repository.UrlRepo;
import com.rishav.urlshortener.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UrlService {
    private final UrlRepo urlRepo;
    private final UserRepo userRepo;

    @Autowired
    public UrlService(UrlRepo urlRepo, UserRepo userRepo){
        this.urlRepo = urlRepo;
        this.userRepo = userRepo;
    }
    public Optional<UrlModel> getUrlByShortKey(String shortKey){
        return urlRepo.findByShortKey(shortKey);
    }
    public UrlResponse createShortUrl(UrlRequest request, String username){
        UserModel user = userRepo.findByUsername(username).orElseThrow(()->new RuntimeException("User not found"));
        String shortKey = UUID.randomUUID().toString().substring(0,6);

        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime expiryDate = createdAt.plusDays(7);

        UrlModel mapping = UrlModel.builder()
                .longUrl(request.getLongUrl())
                .shortKey(shortKey)
                .createdAt(createdAt)
                .expiryDate(expiryDate)
                .clickCount(0)
                .user(user)
                .build();
        urlRepo.save(mapping);

        String shortUrl = "http://localhost:8000/s/"+shortKey;
        return new UrlResponse(shortUrl);
    }
}
