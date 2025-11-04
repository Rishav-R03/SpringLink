package com.rishav.urlshortener.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Getter
@Entity
@AllArgsConstructor
@Table(name="url_mappings")
@Builder
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

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserModel user;
    public void setLongUrl(String longUrl){
        this.longUrl = longUrl;
    }

    public void setShortKey(String shortKey) {
        this.shortKey = shortKey;
    }

    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public void setExpiryDate(LocalDateTime expiryDate) { this.expiryDate = expiryDate; }

    public void setClickCount(int clickCount) { this.clickCount = clickCount; }
}
