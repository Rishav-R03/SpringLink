package com.rishav.urlshortener.services;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class UrlValidationService {
    private static boolean isValid(String url){
        try{
            new URL(url).toURI();
            return url.startsWith("http://") || url.startsWith("https://");
        } catch (MalformedURLException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
