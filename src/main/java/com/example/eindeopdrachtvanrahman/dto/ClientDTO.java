package com.example.eindeopdrachtvanrahman.dto;

import com.example.eindeopdrachtvanrahman.models.CarMechanic;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

public class ClientDTO {
    private Long id;
    @JsonIncludeProperties("id")
    private CarMechanic carMechanic;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CarMechanic getCarMechanic() {
        return carMechanic;
    }

    public void setCarMechanic(CarMechanic carMechanic) {
        this.carMechanic = carMechanic;
    }
}