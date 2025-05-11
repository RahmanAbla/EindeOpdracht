package com.example.eindeopdrachtvanrahman.controllers;

import com.example.eindeopdrachtvanrahman.sequence_diagram.Services.CarMechanicService;
import com.example.eindeopdrachtvanrahman.dto.CarMechanicDTO;
import com.example.eindeopdrachtvanrahman.dto.RecordNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarMechanicController {
    private final CarMechanicService carMechanicService;

    public CarMechanicController(CarMechanicService carMechanicService) {
        this.carMechanicService = carMechanicService;
    }
    @GetMapping("/carmechanics/get")
    public ResponseEntity<List<CarMechanicDTO>>getAllCarMechanics() {

        List<CarMechanicDTO> dtos = carMechanicService.getAllCarMechanics();

        return ResponseEntity.ok(dtos);
    }
    @GetMapping("/carmechanics/{id}")
    public ResponseEntity<CarMechanicDTO>getCarMechanicById(@PathVariable("id") Long id) throws Exception {

        CarMechanicDTO carMechanicDTO = carMechanicService.getCarMechanicById(id);

        return ResponseEntity.ok(carMechanicDTO);
    }
    @PostMapping("/carmechanics/add")
    public ResponseEntity<Object> addCarMechanicsById(@RequestBody CarMechanicDTO dto) {
        CarMechanicDTO carMechanicDTO =carMechanicService.addCarMechanic(dto);
        return ResponseEntity.created(null).body(carMechanicDTO);
    }
    @DeleteMapping("/carmechanics/{id}")
    public ResponseEntity<Object> deleteCarMechanic(@PathVariable Long id) {

        carMechanicService.deleteCarMechanic(id);

        return ResponseEntity.noContent().build();
    }
    @PutMapping("/carmechanics/{id}")
    public ResponseEntity<Object> updateCarMechanic(@PathVariable Long id, @RequestBody CarMechanicDTO newCarMechanic) throws RecordNotFoundException {
        CarMechanicDTO dto=carMechanicService.updateCarMechanic(id, newCarMechanic);
        return ResponseEntity.ok(dto);
    }
    //Dit is een andere manier om het te doen, met twee Pathvariables, maar het kan uiteraard ook anders.
    @PutMapping("/carmechanics/{id}/{garagereceptionistsid}")
    public ResponseEntity<Object>assignGarageReseptionistToCarMechanic(@PathVariable("id") Long id, @PathVariable("garagereceptionistsid") Long garagereceptionistsid) throws RecordNotFoundException {
        carMechanicService.assignGarageReseptionistToCarMechanic(id,garagereceptionistsid );
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/carmechanics/{id}/image/{nameOfImage}")
    @Transactional
    public void assignImageToCarMechanic(@PathVariable("id") Long id, @PathVariable("nameOfImage") String nameOfImage) throws RecordNotFoundException {
        carMechanicService.assignImageToCarMechanic(id, nameOfImage);
    }

    @PutMapping("/carmechanics/user/{id}/{userName}")
    public void assignUserToCarMechanic(@PathVariable("id") Long id, @PathVariable("userName") String userName) throws RecordNotFoundException {
        carMechanicService.assignUserToCarMechanic(id,userName);
    }
}
