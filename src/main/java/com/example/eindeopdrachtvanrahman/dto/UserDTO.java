package com.example.eindeopdrachtvanrahman.dto;

import com.example.eindeopdrachtvanrahman.models.Authority;

import java.util.Set;

public class UserDTO {
    public String username;
    public String password;
    public Boolean enabled;
    public String apikey;
    public String email;
    public Set<Authority> authorities;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public String getApikey() {
        return apikey;
    }

    public String getEmail() {
        return email;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

}












// oud
//import com.example.eindeopdrachtvanrahman.models.CarMechanic;
//import com.example.eindeopdrachtvanrahman.models.Client;
//import com.example.eindeopdrachtvanrahman.models.GarageReceptionist;


//public class UserDTO {
//    private Long id;
//    private String name;
//    private String email;
//   private Client client;
//    private GarageReceptionist garageReceptionist;
//    private CarMechanic carMechanic;
//    private int age;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
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
//    public GarageReceptionist getGarageReceptionist() {
//        return garageReceptionist;
//    }
//
//    public void setGarageReceptionist(GarageReceptionist garageReceptionist) {
//        this.garageReceptionist = garageReceptionist;
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

