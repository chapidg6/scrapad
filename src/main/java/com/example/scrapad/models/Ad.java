package com.example.scrapad.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.UUID;
import java.util.List;

@Entity
@Table(name = "ad")
public class Ad {
    @Id
    private UUID id;
    private String name;
    private Integer amount;
    private double price;

   @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(
        name = "ad_material",
        joinColumns = @JoinColumn(name = "ad_id"),
        inverseJoinColumns = @JoinColumn(name = "material_id")
    )
    private List<Material> materials;

    public Ad(UUID id, String name, Integer amount, double price, List <Material> materials) {
        super();
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.materials = materials;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public List<Material> getMaterials() {
        return materials;
    }


    public void setMaterials(List<Material> materials) {
        this.materials = materials;
    }

   
}
