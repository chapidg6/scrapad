package com.example.scrapad;

import com.example.scrapad.services.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScrapadApplication  {

    @Autowired
    private AdService adService;

    public static void main(String[] args) {
        SpringApplication.run(ScrapadApplication.class, args);
    }

   // @Override // 
   // public void run(String... args) throws Exception {
        // Llamar al método para crear y guardar el anuncio
        //adService.createAndSaveAd();
    //}
}
