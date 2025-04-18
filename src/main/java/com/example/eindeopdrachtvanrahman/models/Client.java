package com.example.eindeopdrachtvanrahman.models;

import jakarta.persistence.*;

@Entity
@Table(name ="clients" )
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
    private String email;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "garageReceptionist_id")
    private GarageReceptionist garageReceptionist;

    public Client(Long id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public Client() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public GarageReceptionist getGarageReceptionist() {
        return garageReceptionist;
    }

    public void setGarageReceptionist(GarageReceptionist garageReceptionist) {
        this.garageReceptionist = garageReceptionist;
    }
}



