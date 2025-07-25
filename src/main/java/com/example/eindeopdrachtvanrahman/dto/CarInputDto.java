package com.example.eindeopdrachtvanrahman.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

public class CarInputDto {
   @NotNull(message = "Brand can not be emmpty")
    private String brand;
    @NotNull(message = "model can not be emmpty")
    private String model;
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int manufacturingyear;
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

    public void setManufacturingyear(int manufacturingYear) {
        this.manufacturingyear = manufacturingYear;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
