package com.example.scrapad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.scrapad.models.Ad;

import java.util.UUID;
public interface AdRepository extends JpaRepository<Ad, UUID> {
    
}
