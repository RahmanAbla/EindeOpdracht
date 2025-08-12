package com.example.eindeopdrachtvanrahman.controllers;


import com.example.eindeopdrachtvanrahman.Services.RepairService;
import com.example.eindeopdrachtvanrahman.dto.RepairDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RepairController {
    private final RepairService repairService;

    public RepairController(RepairService repairService) {
        this.repairService = repairService;
    }

    @GetMapping("/repairs")
    public ResponseEntity<List<RepairDTO>> getAllRepairs() {
        List<RepairDTO> dtos = repairService.getAllRepairs();

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/repairs/{id}")
    public ResponseEntity<RepairDTO> getRepair(@PathVariable("id") Long id) {
        RepairDTO repairDTO = repairService.getRepairById(id);

        return ResponseEntity.ok(repairDTO);
    }

    @PostMapping("/repairs")
    public ResponseEntity<Object> addRepair(@RequestBody RepairDTO dto) {
        RepairDTO repairDTO = repairService.addRepair(dto);
        return ResponseEntity.created(null).body(repairDTO);
    }

    @DeleteMapping("/repairs/{id}")
    public ResponseEntity<Object> deleteRepair(@PathVariable Long id) {

        repairService.deleteRepair(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/repairs/{id}")
    public ResponseEntity<Object> updateRepair(@PathVariable Long id, @Valid @RequestBody RepairDTO newRepair) {
        RepairDTO dto = repairService.updateRepair(id, newRepair);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("/repairs/{id}/car/{carid}")
    public ResponseEntity<Object> assignCarToRepair(@PathVariable("id") Long id, @PathVariable("carid") Long carid) {
        repairService.assignCarToRepair(id, carid);
        return ResponseEntity.noContent().build();
    }

    //Dit is een andere manier om het te doen, met twee Pathvariables, maar het kan uiteraard ook anders.
    @PutMapping("/repairs/{id}/carmechanic/{carmechanicsid}")
    public ResponseEntity<Object> assignCarMechanicToRepair(@PathVariable("id") Long id, @PathVariable("carmechanicsid") Long carmechanicsid) {
        repairService.assignCarMechanicToRepair(id, carmechanicsid);
        return ResponseEntity.noContent().build();
    }
}
