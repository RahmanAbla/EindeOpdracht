package com.example.eindeopdrachtvanrahman.models;

import jakarta.persistence.*;

@Entity
@Table(name = "inspections")
public class Inspection {
    private boolean status;
    private String report;
    private Double inspectionCost;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Inspection(boolean status, String report, Double inspectionCost, Long id) {
        this.status = status;
        this.report = report;
        this.inspectionCost = inspectionCost;
        this.id = id;
    }

    public Inspection() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public Double getInspectionCost() {
        return inspectionCost;
    }

    public void setInspectionCost(Double inspectionCost) {
        this.inspectionCost = inspectionCost;
    }

}
