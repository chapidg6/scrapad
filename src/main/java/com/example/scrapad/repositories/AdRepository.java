package com.example.scrapad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.scrapad.models.Ad;

import java.util.List;
import java.util.UUID;
public interface AdRepository extends JpaRepository<Ad, UUID> {
    
    @Query("SELECT a FROM Ad a JOIN a.materials m WHERE LOWER(a.name) LIKE LOWER(CONCAT('%', :term, '%')) OR LOWER(m.name) LIKE LOWER(CONCAT('%', :term, '%'))")
    List<Ad> searchAds(@Param("term") String term);
}
