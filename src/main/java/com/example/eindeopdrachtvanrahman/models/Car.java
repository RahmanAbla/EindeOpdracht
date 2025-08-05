package com.example.eindeopdrachtvanrahman.models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Entity
@Table(name = "cars")
public class Car {
  @OneToOne(mappedBy ="car")
    Repair repair;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;
    @OneToMany(mappedBy ="car")
    private List<Invoice> invoices ;
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

    public Car(Long carId) {
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

    public Repair getRepair() {
        return repair;
    }

    public void setRepair(Repair repair) {
        this.repair = repair;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }
}
