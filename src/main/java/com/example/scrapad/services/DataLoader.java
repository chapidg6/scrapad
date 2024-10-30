package com.example.scrapad.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {

   @Autowired
    private MaterialService materialService;

    @Autowired
    private AdService adService;

    @EventListener(ApplicationReadyEvent.class)
    public void loadData() {
      
        adService.createAndSaveAdsFromCsv("src/main/resources/data/ad.csv");

    } 
}
