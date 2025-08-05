package com.example.eindeopdrachtvanrahman.models;

import jakarta.persistence.*;

@Entity
@Table(name = "repairs")
public class Repair {
   private String description;
   private String treatment;
   private boolean status;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Repair() {
        this.id = id;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carMechanic_id")
    private CarMechanic carMechanic;
   @OneToOne
   private Car car;

    public Repair(String description, String treatment, boolean status, Long id, Car car) {
        this.description = description;
        this.treatment = treatment;
        this.status = status;
        this.id = id;
       this.car = car;
    }

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

    public Car getCar() {
        return car;
    }

    public CarMechanic getCarMechanic() {
        return carMechanic;
    }

    public void setCarMechanic(CarMechanic carMechanic) {
        this.carMechanic = carMechanic;
    }

    public void setCar(Car car) {
        this.car = car;

    }
}
