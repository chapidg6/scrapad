package com.example.scrapad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.scrapad.services.AdService;


import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/scrapad")
public class AdController {
      @Autowired
    private AdService adService;

    @GetMapping("/search")
    public List<Map<String, ?>> searchAds(@RequestParam("term") String term) {
        return adService.searchAds(term);
    }

     @GetMapping("/addetail/{id}")
     public ResponseEntity<Map<String, Object>> getAdDetail(@PathVariable("id") UUID adId) {
        Map<String, Object> response = adService.getAdDetail(adId);
        if (response == null || response.isEmpty()) {
            // Si no se encuentra el anuncio, se duvuelve un 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(Map.of("error", "Anuncio no encontrado"));
        }

        return ResponseEntity.ok(response);
    }
}
