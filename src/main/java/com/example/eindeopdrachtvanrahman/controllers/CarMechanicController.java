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
    @GetMapping("/carmechanics")
    public ResponseEntity<List<CarMechanicDTO>>getAllCarMechanics() {

        List<CarMechanicDTO> dtos = carMechanicService.getAllCarMechanics();

        return ResponseEntity.ok(dtos);
    }
    @GetMapping("/carmechanics/{id}")
    public ResponseEntity<CarMechanicDTO>getCarMechanicById(@PathVariable("id") Long id) throws Exception {

        CarMechanicDTO carMechanicDTO = carMechanicService.getCarMechanicById(id);

        return ResponseEntity.ok(carMechanicDTO);
    }
    @PostMapping("/carmechanics")
    public ResponseEntity<Object>  getCarMechanicsById(@RequestBody CarMechanicDTO dto) {
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
}
