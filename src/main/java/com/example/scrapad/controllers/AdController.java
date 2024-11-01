package com.example.scrapad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.scrapad.dtos.AdDTO;
import com.example.scrapad.dtos.AdDetailDTO;
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
    public List<AdDTO> searchAds(@RequestParam("term") String term) {
        return adService.searchAds(term);
    }

     @GetMapping("/addetail/{id}")
    public ResponseEntity<AdDetailDTO> getAdDetail(@PathVariable("id") UUID adId) {
    AdDetailDTO response = adService.getAdDetail(adId);
    if (response == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body(null);
    }

    return ResponseEntity.ok(response);
}
}
