package com.rishav.urlshortener.services;

import com.rishav.urlshortener.DTO.UrlRequest;
import com.rishav.urlshortener.DTO.UrlResponse;
import com.rishav.urlshortener.models.UrlModel;
import com.rishav.urlshortener.repository.UrlRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UrlService {
    private final UrlRepo urlRepo;
    @Autowired
    public UrlService(UrlRepo urlRepo){
        this.urlRepo = urlRepo;
    }
    public Optional<UrlModel> getUrlByShortKey(String shortKey){
        return urlRepo.findByShortKey(shortKey);
    }
    public UrlResponse createShortUrl(UrlRequest request){
        //1. Generate short key
        String shortKey = UUID.randomUUID().toString().substring(0,6);
        //2. Time
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime expiryDate = createdAt.plusDays(7);
        //3. Saving
        UrlModel mapping = new UrlModel(request.getLongUrl(),shortKey,createdAt,expiryDate);
        urlRepo.save(mapping);
        //3. Return shortURl
        String shortUrl = "http://localhost:8000/s/"+shortKey;
        return new UrlResponse(shortUrl);
    }
}
