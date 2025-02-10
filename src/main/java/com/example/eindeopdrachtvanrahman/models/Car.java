package com.example.eindeopdrachtvanrahman.models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String brand;
    @Column(nullable = false)
    private String model;
    @Column(nullable = false)
    private int manufacturingyear;


    public Car(String brand, String model, int manufacturingyear, Long id) {
        this.brand = brand;
        this.model = model;
        this.manufacturingyear = manufacturingyear;
        this.id = id;
    }

    public Car() {

    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getManufacturingyear() {
        return manufacturingyear;
    }

    public void setManufacturingyear(int manufacturingyear) {
        this.manufacturingyear = manufacturingyear;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
