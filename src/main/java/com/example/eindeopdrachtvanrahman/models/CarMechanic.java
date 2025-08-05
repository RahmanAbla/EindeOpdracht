package com.example.eindeopdrachtvanrahman.models;

import com.example.eindeopdrachtvanrahman.dto.CarMechanicDTO;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name ="carmechanics" )
public class CarMechanic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy ="carMechanic")
    private List<Repair>repairs ;
    @OneToMany(mappedBy ="carMechanic")
    private List<Invoice>invoices ;
    @OneToMany(mappedBy ="carMechanic")
    private List<Inspection>inspections ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "garageReceptionist_id")
    private GarageReceptionist garageReceptionist;

    @OneToOne()
    @JoinColumn(name = "imageData")
    private ImageData imageData;

    @OneToOne
    private User user;

    public CarMechanic(Long id, GarageReceptionist garageReceptionist, ImageData imageData, User user) {
        this.id = id;
        this.garageReceptionist = garageReceptionist;
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

    public GarageReceptionist getGarageReceptionist() {
        return garageReceptionist;
    }

    public void setGarageReceptionist(GarageReceptionist garageReceptionist) {
        this.garageReceptionist = garageReceptionist;
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
}


