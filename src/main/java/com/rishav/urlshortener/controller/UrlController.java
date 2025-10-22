package com.rishav.urlshortener.controller;

import com.rishav.urlshortener.DTO.UrlRequest;
import com.rishav.urlshortener.DTO.UrlResponse;
import com.rishav.urlshortener.services.UrlService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController// @Controller + @ResponseBody
@RequestMapping("/api")
public class UrlController {
//    private static final Logger logger = (Logger) LoggerFactory.getLogger(UrlController.class);
    private final UrlService urlService;
    @Autowired
    public UrlController(UrlService urlService){
        this.urlService =urlService;
    }
    @GetMapping
    public String helloWorld(){
        return "Hello World";
    }
    @PostMapping("/shorten")
    public ResponseEntity<UrlResponse> shortenUrl(@RequestBody UrlRequest request){
//        logger.info("Received URL: {}");
        UrlResponse response = urlService.createShortUrl(request);
        return ResponseEntity.ok(response);
    }

}
