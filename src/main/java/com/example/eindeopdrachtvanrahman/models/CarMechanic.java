package com.example.eindeopdrachtvanrahman.models;

import jakarta.persistence.*;
@Entity
@Table(name ="carmechanics" )
public class CarMechanic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
}
















// oude code
//@Entity
//@Table(name ="carmechanics" )
//public class CarMechanic {
//    private String name;
//    private int age;
//    private String address;
//    private String email;
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "garageReceptionist_id")
//    private GarageReceptionist garageReceptionist;
//
//    @OneToOne()
//    @JoinColumn(name = "imageData")
//    private ImageData imageData;
//
//    @OneToOne
//    private User user;
//
//    public CarMechanic(String name, int age, String address, String email, Long id) {
//        this.name = name;
//        this.age=age;
//        this.address = address;
//        this.email = email;
//        this.id = id;
//    }
//
//    public CarMechanic() {
//
//    }
//
//    public ImageData getImageData() {
//        return imageData;
//    }
//
//    public void setImageData(ImageData imageData) {
//        this.imageData = imageData;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public GarageReceptionist getGarageReceptionist() {
//        return garageReceptionist;
//    }
//
//    public void setGarageReceptionist(GarageReceptionist garageReceptionist) {
//        this.garageReceptionist = garageReceptionist;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//}

