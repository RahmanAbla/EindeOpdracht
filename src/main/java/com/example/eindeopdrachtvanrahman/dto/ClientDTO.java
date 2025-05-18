package com.example.eindeopdrachtvanrahman.dto;

public class ClientDTO {
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