package com.example.eindeopdrachtvanrahman.controllers;

import com.example.eindeopdrachtvanrahman.sequence_diagram.Services.GarageReceptionistService;
import com.example.eindeopdrachtvanrahman.dto.GarageReceptionistDTO;
import com.example.eindeopdrachtvanrahman.dto.RecordNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GarageReceptionistController {
    GarageReceptionistService garageReceptionistService;

    public GarageReceptionistController(GarageReceptionistService garageReceptionistService) {
        this.garageReceptionistService = garageReceptionistService;
    }
    @GetMapping("/garagereceptionists")
    public ResponseEntity<List<GarageReceptionistDTO>> getAllGarageReceptionists() {
        List<GarageReceptionistDTO> dtos = garageReceptionistService.getAllGarageReceptionists();

        return ResponseEntity.ok(dtos);
    }
    @GetMapping("/garagereceptionists/{id}")
    public ResponseEntity<GarageReceptionistDTO>getGarageReceptionistById(@PathVariable("id") Long id) throws Exception {

        GarageReceptionistDTO garageReceptionistDTO = garageReceptionistService.getGarageReceptionistById(id);

        return ResponseEntity.ok(garageReceptionistDTO);
    }
    @PostMapping("/garagereceptionists")
    public ResponseEntity<Object> addGarageReceptionist(@RequestBody GarageReceptionistDTO dto) {
        GarageReceptionistDTO garageReceptionistDTO = garageReceptionistService.addGarageReceptionist(dto);
        return ResponseEntity.created(null).body(garageReceptionistDTO);
    }
    @DeleteMapping("/garagereceptionists/{id}")
    public ResponseEntity<Object> deletegarageReseptionist(@PathVariable Long id) {

        garageReceptionistService.deletegarageReseptionist(id);

        return ResponseEntity.noContent().build();
    }
    @PutMapping("/garagereceptionists/{id}")
    public ResponseEntity<Object> updateGarageReceptionist(@PathVariable Long id, @RequestBody GarageReceptionistDTO newGarageReceptionist) throws RecordNotFoundException {
    GarageReceptionistDTO dto=garageReceptionistService.updateGarageReceptionist(id,newGarageReceptionist);
        return ResponseEntity.ok(dto);
    }

}
