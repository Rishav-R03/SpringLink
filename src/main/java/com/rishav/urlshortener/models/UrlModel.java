package com.rishav.urlshortener.models;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Entity
@Table(name="url_mappings")
public class UrlModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    private String longUrl;

    @Column(nullable = false,unique = true)
    private String shortKey;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime expiryDate;

    @Column(nullable = false)
    private int clickCount = 0;

    public UrlModel(){}
    public UrlModel(String longUrl,String shortKey,LocalDateTime createdAt,LocalDateTime expiryDate){
        this.longUrl = longUrl;
        this.shortKey = shortKey;
        this.createdAt = createdAt;
        this.expiryDate = expiryDate;
    }
    public Long getId(){
        return Id;
    }

    public void setLongUrl(String longUrl){
        this.longUrl = longUrl;
    }

    public String getLongUrl(){
        return longUrl;
    }
    public String getShortKey() {
        return shortKey;
    }
    public void setShortKey(String shortKey) {
        this.shortKey = shortKey;
    }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDateTime expiryDate) { this.expiryDate = expiryDate; }
    public int getClickCount() { return clickCount; }
    public void setClickCount(int clickCount) { this.clickCount = clickCount; }
}
