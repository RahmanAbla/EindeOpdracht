package com.example.eindeopdrachtvanrahman.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Invoice {
    private String description;
    private Double repairCost;
    private Double partCost;
    private Double inspectionCost;
    private double totalCost;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    public Invoice() {

    }

    public Invoice(String description, Double repairCost, Double partCost, Double inspectionCost, double totalCost, Long id) {
        this.description = description;
        this.repairCost = repairCost;
        this.partCost = partCost;
        this.inspectionCost = inspectionCost;
        this.totalCost = totalCost;
        this.id = id;
    }

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
}
