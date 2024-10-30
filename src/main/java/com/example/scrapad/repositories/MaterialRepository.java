package com.example.scrapad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.scrapad.models.Material;
import java.util.UUID;
import java.util.Optional;
public interface MaterialRepository extends JpaRepository<Material, UUID> {
    Material findByName(String name);  
}
