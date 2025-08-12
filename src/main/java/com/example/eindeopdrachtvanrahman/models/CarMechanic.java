package com.example.eindeopdrachtvanrahman.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "carmechanics")
public class CarMechanic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "carMechanic")
    private List<Repair> repairs;
    @OneToMany(mappedBy = "carMechanic")
    private List<Invoice> invoices;
    @OneToMany(mappedBy = "carMechanic")
    private List<Inspection> inspections;
    @OneToMany(mappedBy = "carMechanic")
    private List<Client> clients;


    @OneToOne()
    @JoinColumn(name = "imageData")
    private ImageData imageData;

    @OneToOne
    private User user;

    public CarMechanic(Long id, ImageData imageData, User user) {
        this.id = id;
        this.imageData = imageData;
        this.user = user;
    }

    public CarMechanic() {


    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ImageData getImageData() {
        return imageData;
    }

    public void setImageData(ImageData imageData) {
        this.imageData = imageData;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Repair> getRepairs() {
        return repairs;
    }

    public void setRepairs(List<Repair> repairs) {
        this.repairs = repairs;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    public List<Inspection> getInspections() {
        return inspections;
    }

    public void setInspections(List<Inspection> inspections) {
        this.inspections = inspections;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }
}


