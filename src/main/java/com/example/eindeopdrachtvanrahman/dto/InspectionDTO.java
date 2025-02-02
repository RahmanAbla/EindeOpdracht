package com.example.eindeopdrachtvanrahman.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class InspectionDTO {
    private boolean status;
    private String report;
    private Double inspectionCost;
    private Long id;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
