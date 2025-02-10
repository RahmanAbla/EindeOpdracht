package com.example.eindeopdrachtvanrahman.controllers;

import com.example.eindeopdrachtvanrahman.Services.ClientService;

import com.example.eindeopdrachtvanrahman.dto.ClientDTO;
import com.example.eindeopdrachtvanrahman.dto.RecordNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
    @GetMapping("/clients")
    public ResponseEntity<List<ClientDTO>> getAllClients() {

        List<ClientDTO> dtos = clientService.getAllClients();

        return ResponseEntity.ok(dtos);
    }
    @GetMapping("/clients/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable("id") Long id) throws Exception {

        ClientDTO clientDTO = clientService.getClientById(id);

        return ResponseEntity.ok(clientDTO);
    }
    @PostMapping("/clients")
    public ResponseEntity<Object> addClient(@RequestBody ClientDTO dto) {
        ClientDTO clientDTO = clientService.addClient(dto);
        return ResponseEntity.created(null).body(clientDTO);
    }
    @DeleteMapping("/clients/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable Long id) {

        clientService.deleteClient(id);

        return ResponseEntity.noContent().build();
    }
    @PutMapping("/clients/{id}")
    public ResponseEntity<Object> updateClient(@PathVariable Long id, @RequestBody ClientDTO newClient) throws RecordNotFoundException {
     ClientDTO dto= clientService.updateClient(id, newClient);
        return ResponseEntity.ok(dto);
    }

}
