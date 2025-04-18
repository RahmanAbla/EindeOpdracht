package com.example.eindeopdrachtvanrahman.models;

import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name = "garagereceptionists")
public class GarageReceptionist {
    private String name;
    private int phoneNumber;
    private String email;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy ="garageReceptionist")
    private List<Client> clients;
    @OneToMany(mappedBy ="garageReceptionist")
    private List<CarMechanic> carMechanics;

    public GarageReceptionist(String name, int phoneNumber, String email, Long id) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.id = id;
    }

    public GarageReceptionist() {

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
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
}




