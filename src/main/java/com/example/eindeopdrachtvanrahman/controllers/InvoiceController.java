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
    @PostMapping("/invoices")
    public ResponseEntity<InvoiceDTO> addInvoice(@RequestBody InvoiceDTO dto) {
        InvoiceDTO invoiceDTO = invoiceService.addInvoice(dto);
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

}
