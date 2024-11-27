package com._mx.entity;

import jakarta.persistence.*;

import java.util.Set;

//Con Entity andiamo a dire che questa classe è lo scheletro della tabella su DB
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(nullable = false)
    private Double price;

    private String description;
    private String sku;
    private boolean availability;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany
    @JoinTable(
            name = "product_model",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "model_id"))
    private Set<Model> models;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public String getSku() {
        return sku;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public void setModels(Set<Model> models) {
        this.models = models;
    }

    public Set<Model> getModels() {
        return models;
    }

}
