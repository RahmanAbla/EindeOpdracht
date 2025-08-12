package com.example.eindeopdrachtvanrahman.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carMechanic_id")
    private CarMechanic carMechanic;

    @OneToOne
    private User user;
    @OneToOne(mappedBy = "client")
    private Invoice invoice;
    @OneToMany(mappedBy = "client")
    private List<Car> cars;

    public Client() {

    }

    public Client(Long id, User user, CarMechanic carMechanic) {
        this.id = id;
        this.carMechanic = carMechanic;
        this.user = user;
    }

    public Client(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public CarMechanic getCarMechanic() {
        return carMechanic;
    }

    public void setCarMechanic(CarMechanic carMechanic) {
        this.carMechanic = carMechanic;
    }
}

