package com.example.eindeopdrachtvanrahman.controllers;

import com.example.eindeopdrachtvanrahman.Services.CarService;
import com.example.eindeopdrachtvanrahman.dto.CarDTO;
import com.example.eindeopdrachtvanrahman.dto.CarInputDto;
import com.example.eindeopdrachtvanrahman.dto.RecordNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarController {
    private final CarService carService;
    public CarController(CarService carService) {
        this.carService = carService;
    }
    @GetMapping("/cars")
    public ResponseEntity <List<CarDTO>>getAllCars(){  //moet fixen
        List<CarDTO>dtos=carService.getAllCars();
        return ResponseEntity.ok(dtos);
    }
    @GetMapping("/cars/{id}")
    public ResponseEntity <CarDTO>getCar(@PathVariable("id")Long id) throws Exception {
        CarDTO carDTO=carService.getCarsById(id);
        return ResponseEntity.ok(carDTO);
    }
    @PostMapping("/cars")
    public ResponseEntity<Object> addCar(@RequestBody CarInputDto dto) {
        CarDTO carDTO = carService.addCar(dto);
        return ResponseEntity.created(null).body(carDTO);

    }
    @DeleteMapping("/deletecars/{id}")
    public ResponseEntity<Object> deleteCar(@PathVariable Long id) {

        carService.deleteCar(id);

        return ResponseEntity.noContent().build();
    }
    @PutMapping("/updatecars/{id}")
    public ResponseEntity<Object> updateCar(@PathVariable Long id, @Valid @RequestBody CarInputDto newCar) throws RecordNotFoundException {
        CarDTO dto=carService.updateCar(id ,newCar);
        return ResponseEntity.ok().body(dto);
    }

}
