package com.example.eindeopdrachtvanrahman.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name ="users")
public class User {
    @Id
    @Column(nullable = false,unique = true)
    private String username;
    @Column(nullable = false, length = 255)
    private String password;

    // Deze 3 variabelen zijn niet verplicht.
    // Je mag ook een "String banaan;" toevoegen, als je dat graag wilt.
    @Column(nullable = false)
    private boolean enabled = true;

    @Column
    private String apikey;

    @Column
    private String email;

    @OneToMany(
            targetEntity = Authority.class,
            mappedBy = "username",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    private Set<Authority> authorities = new HashSet<>();

    @OneToOne(mappedBy = "user")
    CarMechanic carMechanic;

    @OneToOne(mappedBy = "user")
    Client client;

    public String getUsername() { return username; }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public boolean isEnabled() { return enabled;}
    public void setEnabled(boolean enabled) { this.enabled = enabled; }
    public String getApikey() { return apikey; }
    public void setApikey(String apikey) { this.apikey = apikey; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email;}

    public Set<Authority> getAuthorities() { return authorities; }
    public void addAuthority(Authority authority) {
        this.authorities.add(authority);
    }
    public void removeAuthority(Authority authority) {
        this.authorities.remove(authority);
    }

    public CarMechanic getCarMechanic() {
        return carMechanic;
    }

    public void setCarMechanic(CarMechanic carMechanic) {
        this.carMechanic = carMechanic;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}


















//  oud
//    @Entity
//    @Table(name ="users")
//    public class User {
//    @Id
//    @Column(nullable = false,unique = true)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
    //    private String name;
//    private String email;
////    @OneToOne
////    private Client client;
////    @OneToOne
////    private GarageReceptionist garageReseptionist;
////    @OneToOne
////   private CarMechanic carMechanic;
//    private int age;


//    public User(String name, String email//Client client, GarageReceptionist garageReseptionist, CarMechanic carMechanic//
//                 ,int age, Long id) {
//        this.name = name;
//        this.email = email;
////        this.client = client;
////        this.garageReseptionist = garageReseptionist;
////        this.carMechanic = carMechanic;
//        this.age = age;
//        this.id = id;
//    }

//    public User() {
//
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
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }

//    public Client getClient() {
//        return client;
//    }
//
//    public void setClient(Client client) {
//        this.client = client;
//    }
//
//    public GarageReceptionist getGarageReseptionist() {
//        return garageReseptionist;
//    }
//
//    public void setGarageReseptionist(GarageReceptionist garageReseptionist) {
//        this.garageReseptionist = garageReseptionist;
//    }
//
//    public CarMechanic getCarMechanic() {
//        return carMechanic;
//    }
//
//    public void setCarMechanic(CarMechanic carMechanic) {
//        this.carMechanic = carMechanic;
//    }

//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//}
