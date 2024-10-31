package com.example.scrapad.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.scrapad.models.Ad;
import com.example.scrapad.models.Material;
import com.example.scrapad.repositories.AdRepository;
import com.example.scrapad.repositories.MaterialRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

import jakarta.transaction.Transactional;



import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class AdService {

    @Autowired
    private AdRepository adRepository;

    
    @Autowired
    private MaterialRepository materialRepository;
    

    @Autowired
    private MaterialService materialService; 


      public void createAndSaveAdsFromCsv(String filePath) {
       // Lista temporal para almacenar los anuncios 
       List<Ad> adList = new ArrayList<>();

       try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
           String[] nextLine;

           // Leer la cabecera y pasar a la siguiente linea
           csvReader.readNext();

           while ((nextLine = csvReader.readNext()) != null) {
            // Linea de csv incorrecta
               if (nextLine.length < 5) {
                   System.err.println("Línea incompleta: " + String.join(",", nextLine));
                   continue;
               }

               UUID adId = UUID.fromString(nextLine[0].trim());
               String adName = nextLine[1].trim();
               Integer adAmount = Integer.parseInt(nextLine[2].trim());
               double adPrice = Double.parseDouble(nextLine[3].trim())/100;
               String materialName = nextLine[4].trim();

               // Verificar si el material ya existe 
               Material material = materialRepository.findByName(materialName);
               if (material == null) {
                   // Si no existe, crearlo y guardarlo
                   material = new Material(materialName);
                   materialRepository.save(material);
               }

               // Buscar si ya existe un anuncio con el mismo ID en la lista 
               Ad ad = findAdById(adList, adId);
               if (ad == null) {
                   // Si no existe, crear un nuevo anuncio y agregarlo a la lista
                   ad = new Ad(adId, adName, adAmount, adPrice, new ArrayList<>());
                   adList.add(ad);
               }

               // Agregar el material al anuncio (solo si no está ya en la lista de materiales del anuncio)
               if (!ad.getMaterials().contains(material)) {
                   ad.getMaterials().add(material);
               }
           }

           // Guardar cada anuncio con su lista de materiales en la base de datos
           for (Ad ad : adList) {
               adRepository.save(ad);
           }

       } catch (IOException | CsvException e) {
           System.err.println("Error al leer o procesar el archivo CSV: " + e.getMessage());
       } catch (Exception e) {
           System.err.println("Error inesperado: " + e.getMessage());
       }
   }

   // Método auxiliar para buscar un anuncio por ID en la lista temporal
   private Ad findAdById(List<Ad> adList, UUID adId) {
       for (Ad ad : adList) {
           if (ad.getId().equals(adId)) {
               return ad;
           }
       }
       return null;
   }

   public List<Map<String, ?>> searchAds(String term) {  
    
    List<Ad> resultAds = adRepository.searchAds(term);

    // Convertir a lista de mapas para la respuesta JSON
    return resultAds.stream()
            .map(ad -> Map.of(
                    "id", ad.getId().toString(),
                    "name", ad.getName(),
                    "amount", ad.getAmount(),
                    "price", ad.getPrice()  
            ))
            .collect(Collectors.toList());
}

public Map<String, Object> getAdDetail(UUID adId) {
    Optional<Ad> adOptional = adRepository.findById(adId);

    if (adOptional.isPresent()) {
        Ad ad = adOptional.get();
        Map<String, Object> response = new HashMap<>();
        response.put("id", ad.getId());
        response.put("name", ad.getName());
        response.put("amount", ad.getAmount());
        response.put("price", ad.getPrice());
        response.put("relatedAds", getRelatedAds(ad));

        return response;
    } else {
        return null; // Devuelve un Map vacío
    }
}

private List<Map<String, Object>> getRelatedAds(Ad ad) {
    List<Ad> relatedAds = adRepository.findRelatedAdsByMaterial(ad.getMaterials(), ad.getId());
    return relatedAds.stream().map(this::mapAdToResponse).toList(); 
}

private Map<String, Object> mapAdToResponse(Ad ad) {
    Map<String, Object> adResponse = new HashMap<>();
    adResponse.put("id", ad.getId());
    adResponse.put("name", ad.getName());
    adResponse.put("amount", ad.getAmount());
    adResponse.put("price", ad.getPrice());
   
    return adResponse;
}
   /* 
   public void createAndSaveAd() {
    
    UUID adId = UUID.randomUUID();
    String adName = "Ad Example";
    Integer adAmount = 10;
    double adPrice = 29.99;

    
    Material material1 = new Material("Material 1");
    Material material2 = new Material("Material 2");

    
    List<Material> materials = new ArrayList<>();
    materials.add(material1);
    materials.add(material2);

    
    Ad ad = new Ad(adId, adName, adAmount, adPrice, materials);

    // Guardar el anuncio utilizando el repositorio correspondiente
    adRepository.save(ad);
}*/


}