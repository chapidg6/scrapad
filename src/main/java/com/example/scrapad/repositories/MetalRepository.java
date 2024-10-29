package com.example.scrapad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.scrapad.models.Material;
import java.util.UUID;

public interface MetalRepository extends JpaRepository<Material, UUID> {
    
}
