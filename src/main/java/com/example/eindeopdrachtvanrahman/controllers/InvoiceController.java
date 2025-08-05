package com.example.eindeopdrachtvanrahman.controllers;

import com.example.eindeopdrachtvanrahman.Services.InvoiceService;
import com.example.eindeopdrachtvanrahman.dto.InvoiceDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class InvoiceController {
    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }


    @GetMapping("/invoices")
    public ResponseEntity<List<InvoiceDTO>> getAllInvoice() {

        List<InvoiceDTO> dtos = invoiceService.getAllInvoices();

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/invoices/{id}")
    public ResponseEntity<InvoiceDTO> getInvoicesById(@PathVariable("id") Long id ) {
        InvoiceDTO invoiceDTO = invoiceService.getInvoicesById(id);
        return ResponseEntity.ok(invoiceDTO);
    }
    @PostMapping("/invoices/{repairCost}/{partCost}/{inspectionCost}")
    public ResponseEntity<InvoiceDTO> addInvoice(@RequestBody InvoiceDTO dto,@PathVariable("repairCost") Double repairCost,@PathVariable("partCost") Double partCost,@PathVariable("inspectionCost") Double inspectionCost) {
        InvoiceDTO invoiceDTO = invoiceService.addInvoice(dto, repairCost,partCost,inspectionCost);
        return ResponseEntity.created(null).body(invoiceDTO);
    }
    @DeleteMapping("/invoices/{id}")
    public ResponseEntity<Object> deleteInvoice(@PathVariable Long id) {

        invoiceService.deleteInvoice(id);

        return ResponseEntity.noContent().build();
    }
    @PutMapping("/invoices/{id}")
    public ResponseEntity<Object> updateInvoice(@PathVariable Long id, @Valid @RequestBody InvoiceDTO newInvoice) {
   InvoiceDTO dto=invoiceService.updateInvoice(id, newInvoice);
        return ResponseEntity.ok(dto);
    }
    @PutMapping("/invoices/{id}/carmechanic/{carmechanicsid}")
    public ResponseEntity<Object>assignCarMechanicToInvoice(@PathVariable("id") Long id, @PathVariable("carmechanicsid") Long carmechanicsid) {
        invoiceService.assignCarMechanicToInvoice(id,carmechanicsid);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/invoices/{id}/client/{clientsid}")
    public ResponseEntity<Object>assignClientToInvoice(@PathVariable("id") Long id, @PathVariable("clientsid") Long clientsid) {
        invoiceService.assignClientToInvoice(id,clientsid);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/invoices/{id}/car/{carsid}")
    public ResponseEntity<Object>assignCarToInvoice(@PathVariable("id") Long id, @PathVariable("carsid") Long carsid) {
        invoiceService.assignCarToInvoice(id,carsid);
        return ResponseEntity.noContent().build();
    }
}
