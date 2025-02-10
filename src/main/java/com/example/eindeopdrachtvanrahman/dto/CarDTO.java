package com.example.eindeopdrachtvanrahman.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class CarDTO {
    private String brand;
    private String model;
    private int manufacturingyear;
    private Long id;

    public CarDTO(String brand, String model, int manufacturingyear, Long id) {
        this.brand = brand;
        this.model = model;
        this.manufacturingyear = manufacturingyear;
        this.id = id;
    }

    public CarDTO() {
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getManufacturingyear() {
        return manufacturingyear;
    }

    public void setManufacturingyear(int manufacturingyear) {
        this.manufacturingyear = manufacturingyear;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
