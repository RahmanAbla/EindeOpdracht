package com.example.eindeopdrachtvanrahman.dto;

import com.example.eindeopdrachtvanrahman.models.Car;
import com.example.eindeopdrachtvanrahman.models.CarMechanic;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

public class RepairDTO {
    private String description;
    private String treatment;
    private boolean status;
    private Long id;
    @JsonIncludeProperties("id")
    private CarMechanic carMechanic;
    @JsonIncludeProperties("id")
    private Car car;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

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

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
