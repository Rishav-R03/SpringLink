package com.rishav.urlshortener.repository;

import com.rishav.urlshortener.models.UrlModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRepo extends JpaRepository<UrlModel,Long> {
    Optional<UrlModel> findByShortKey(String shortKey);
}
