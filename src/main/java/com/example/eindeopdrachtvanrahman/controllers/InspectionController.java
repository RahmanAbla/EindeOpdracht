package com.example.eindeopdrachtvanrahman.controllers;

import com.example.eindeopdrachtvanrahman.Services.InspectionServise;

import com.example.eindeopdrachtvanrahman.dto.InspectionDTO;
import com.example.eindeopdrachtvanrahman.dto.RecordNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InspectionController { private final InspectionServise inspectionServise;
    public InspectionController(InspectionServise inspectionServise) {
        this.inspectionServise = inspectionServise;
    }
    @GetMapping("/AllInspections")
    public ResponseEntity<List<InspectionDTO>>getAllInspections() {

        List<InspectionDTO> dtos = inspectionServise.getAllInspections();

        return ResponseEntity.ok(dtos);
    }
    @GetMapping("/Inspection/{id}")
    public ResponseEntity<InspectionDTO> getInspectionById(@PathVariable("id") Long id) throws Exception {

        InspectionDTO inspectionDTO = inspectionServise.getInspectionById(id);

        return ResponseEntity.ok(inspectionDTO);
    }
    @PostMapping("/Inspection")
    public ResponseEntity<Object> addInspection(@RequestBody InspectionDTO dto) {
        InspectionDTO inspectionDTO =inspectionServise.addInspection(dto);
        return ResponseEntity.created(null).body(inspectionDTO);
    }
    @DeleteMapping("/deleteInspection/{id}")
    public ResponseEntity<Object> deleteInspection(@PathVariable Long id) {

        inspectionServise.deleteInspection(id);

        return ResponseEntity.noContent().build();
    }
    @PutMapping("/Inspection/{id}")
    public ResponseEntity<Object> updateInspection(@PathVariable Long id,@Valid @RequestBody InspectionDTO newInspection) throws RecordNotFoundException {
      InspectionDTO dto=inspectionServise.updateInspection(id,newInspection);
        return ResponseEntity.ok().body(dto);
    }
}
