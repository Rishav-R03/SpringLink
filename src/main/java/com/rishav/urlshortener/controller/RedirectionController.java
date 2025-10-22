package com.rishav.urlshortener.controller;

import com.rishav.urlshortener.models.UrlModel;
import com.rishav.urlshortener.repository.UrlRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
public class RedirectionController {
    private final UrlRepo urlRepo;
    @Autowired
    public RedirectionController(UrlRepo urlRepo){
        this.urlRepo = urlRepo;
    }
    @GetMapping("/s/{shortKey}")
    public ResponseEntity<Void> redirectToLongUrl(@PathVariable String shortKey){
        UrlModel urlModel = urlRepo.findByShortKey(shortKey).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Short URL not found"));

        if(LocalDateTime.now().isAfter(urlModel.getExpiryDate())){
            throw new ResponseStatusException(HttpStatus.GONE,"Short URL expired");
        }
        // Increment click count
        urlModel.setClickCount(urlModel.getClickCount()+1);
        urlRepo.save(urlModel);
        // Respond with Http 302 redirect
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(urlModel.getLongUrl()))
                .build();
    }
}
