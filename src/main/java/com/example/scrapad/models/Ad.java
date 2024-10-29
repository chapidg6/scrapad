package com.example.scrapad.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.util.UUID;

@Entity
public class Ad {
    @Id
    private UUID id;
    private String name;
    private Integer amount;
    private Integer price;

    @ManyToOne
    private Material material;

    public Ad(UUID id, String name, Integer amount, Integer price, Material material) {
        super();
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.material = material;
    }

    
    public Ad() {
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    
}
