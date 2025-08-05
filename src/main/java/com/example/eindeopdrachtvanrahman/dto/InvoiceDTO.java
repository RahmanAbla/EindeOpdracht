package com.example.eindeopdrachtvanrahman.dto;

import com.example.eindeopdrachtvanrahman.models.Car;
import com.example.eindeopdrachtvanrahman.models.CarMechanic;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class InvoiceDTO {
    private String description;
    private Double repairCost;
    private Double partCost;
    private Double inspectionCost;
    private double totalCost;
    private Long id;
    private CarMechanic carMechanic;
    private long clientId;
    private Car car;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getRepairCost() {
        return repairCost;
    }

    public void setRepairCost(Double repairCost) {
        this.repairCost = repairCost;
    }

    public Double getPartCost() {
        return partCost;
    }

    public void setPartCost(Double partCost) {
        this.partCost = partCost;
    }

    public Double getInspectionCost() {
        return inspectionCost;
    }

    public void setInspectionCost(Double inspectionCost) {
        this.inspectionCost = inspectionCost;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
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

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
