package com.example.eindeopdrachtvanrahman.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class CarMechanicDTO {
        private String name;
        private int age;
        private String address;
       private String email;
        private Long id;
        private GarageReceptionistDTO garageReceptionistDTO;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

