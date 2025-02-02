package com.example.eindeopdrachtvanrahman.controllers;

import com.example.eindeopdrachtvanrahman.Services.CarMechanicService;
import com.example.eindeopdrachtvanrahman.dto.CarMechanicDTO;
import com.example.eindeopdrachtvanrahman.dto.RecordNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarMechanicController {
    private CarMechanicService carMechanicService;

    public CarMechanicController(CarMechanicService carMechanicService) {
        this.carMechanicService = carMechanicService;
    }
    @GetMapping("/allCarMechanics")
    public ResponseEntity<List<CarMechanicDTO>>getAllCarMechanics() {

        List<CarMechanicDTO> dtos = carMechanicService.getAllCarMechanics();

        return ResponseEntity.ok(dtos);
    }
    @GetMapping("/CarMechanic/{id}")
    public ResponseEntity<CarMechanicDTO>getCarMechanicById(@PathVariable("id") Long id) throws Exception {

        CarMechanicDTO carMechanicDTO = carMechanicService.getCarMechanicById(id);

        return ResponseEntity.ok(carMechanicDTO);
    }
    @PostMapping("/addCarMechanic")
    public ResponseEntity<Object>  getCarMechanicsById(@RequestBody CarMechanicDTO dto) {
        CarMechanicDTO carMechanicDTO =carMechanicService.addCarMechanic(dto);
        return ResponseEntity.created(null).body(carMechanicDTO);
    }
    @DeleteMapping("/deleteCarMechanic/{id}")
    public ResponseEntity<Object> deleteCarMechanic(@PathVariable Long id) {

        carMechanicService.deleteCarMechanic(id);

        return ResponseEntity.noContent().build();
    }
    @PutMapping("/updateCarMechanic/{id}")
    public ResponseEntity<Object> updateCarMechanic(@PathVariable Long id, @RequestBody CarMechanicDTO newCarMechanic) throws RecordNotFoundException {
        CarMechanicDTO dto=carMechanicService.updateCarMechanic(id, newCarMechanic);
        return ResponseEntity.ok(dto);
    }
}
