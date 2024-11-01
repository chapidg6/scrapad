package com.example.scrapad.dtos;

import java.util.UUID;

public class AdDTO {

    private UUID id;
    private String name;
    private Integer amount;
    private double price;
    

    public AdDTO(UUID id, String name, Integer amount, double price) {
        super();
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.price = price;
    }


    public AdDTO() {
    }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getAmount() {
        return amount;
    }
    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    
}
