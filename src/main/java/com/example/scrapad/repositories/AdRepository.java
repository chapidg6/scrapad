package com.example.scrapad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.scrapad.models.Ad;
import com.example.scrapad.models.Material;

import java.util.List;
import java.util.UUID;
public interface AdRepository extends JpaRepository<Ad, UUID> {
   //Search Service 
    @Query(" SELECT a FROM Ad a JOIN a.materials m WHERE(LOWER(a.name) LIKE %:term%) OR (LOWER(m.name) LIKE %:term%)")
    List<Ad> searchAds(@Param("term") String term);
    //Detail Service
    @Query("SELECT a FROM Ad a JOIN a.materials m WHERE m IN :materials AND a.id != :excludeId")
    List<Ad> findRelatedAdsByMaterial(List<Material> materials, UUID excludeId);
}
