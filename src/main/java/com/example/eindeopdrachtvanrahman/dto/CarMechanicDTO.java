package com.example.eindeopdrachtvanrahman.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class CarMechanicDTO {

    private Long id;
    private GarageReceptionistDTO garageReceptionistDTO;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GarageReceptionistDTO getGarageReceptionistDTO() {
        return garageReceptionistDTO;
    }

    public void setGarageReceptionistDTO(GarageReceptionistDTO garageReceptionistDTO) {
        this.garageReceptionistDTO = garageReceptionistDTO;
    }
}

