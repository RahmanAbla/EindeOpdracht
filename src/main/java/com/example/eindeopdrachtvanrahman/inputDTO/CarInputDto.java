package com.example.eindeopdrachtvanrahman.inputDTO;

import com.example.eindeopdrachtvanrahman.models.Client;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

public class CarInputDto {
    @NotNull(message = "Brand can not be emmpty")
    private String brand;
    @NotNull(message = "model can not be emmpty")
    private String model;
    @Id
    private Long id;
    private int manufacturingyear;

    @JsonIncludeProperties("id")
    private Client client;

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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
