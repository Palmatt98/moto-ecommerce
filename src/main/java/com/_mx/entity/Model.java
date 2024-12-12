package com._mx.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "models")
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer year;
    private Integer engine_quantity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @JsonIgnore
    @ManyToMany(mappedBy = "models")
    private Set<Product> products;

    public class ModelRequest {
        private Long modelId;


        public Long getModelId() {
            return modelId;
        }

        public void setModelId(Long modelId) {
            this.modelId = modelId;
        }
    }

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

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getYear() {
        return year;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setEngine_quantity(Integer engineQuantity) {
        this.engine_quantity = engineQuantity;
    }

    public Integer getEngine_quantity() {
        return engine_quantity;
    }

}
