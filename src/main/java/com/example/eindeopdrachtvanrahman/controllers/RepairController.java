package com.example.eindeopdrachtvanrahman.controllers;


import com.example.eindeopdrachtvanrahman.sequence_diagram.Services.RepairService;
import com.example.eindeopdrachtvanrahman.dto.RecordNotFoundException;
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
    public ResponseEntity<List<RepairDTO>>getAllRepairs() {
        List<RepairDTO> dtos = repairService.getAllRepairs();

        return ResponseEntity.ok(dtos);
    }
    @GetMapping("/repairs/{id}")
    public ResponseEntity<RepairDTO> getRepair(@PathVariable("id") Long id) throws Exception {
        RepairDTO repairDTO =repairService.getRepairById(id);

        return ResponseEntity.ok(repairDTO);
    }
    @PostMapping("/repairs")
    public ResponseEntity<Object> addRepair(@RequestBody RepairDTO dto) {
            RepairDTO repairDTO=repairService.addRepair(dto);
        return ResponseEntity.created(null).body(repairDTO);
    }
    @DeleteMapping("/repairs/{id}")
    public ResponseEntity<Object>deleteRepair(@PathVariable Long id) {

        repairService.deleteRepair(id);

        return ResponseEntity.noContent().build();
    }
    @PutMapping("/repairs/{id}")
    public ResponseEntity<Object> updateRepair(@PathVariable Long id, @Valid @RequestBody RepairDTO newRepair) throws RecordNotFoundException {
       RepairDTO dto=repairService.updateRepair(id,newRepair);
       return ResponseEntity.ok().body(dto);
    }
}
