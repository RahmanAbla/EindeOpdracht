package com.example.eindeopdrachtvanrahman.controllers;

import com.example.eindeopdrachtvanrahman.Services.ClientService;

import com.example.eindeopdrachtvanrahman.dto.ClientDTO;
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
    public ResponseEntity<ClientDTO> getClientById(@PathVariable("id") Long id) {

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
    public ResponseEntity<Object> updateClient(@PathVariable Long id, @RequestBody ClientDTO newClient) {
        ClientDTO dto= clientService.updateClient(id, newClient);
        return ResponseEntity.ok(dto);
    }

    //Dit is een andere manier om het te doen, met twee Pathvariables, maar het kan uiteraard ook anders.
//    @PutMapping("/clients/{id}/{garagereceptionistsid}")
    @PutMapping("/clients/{id}/garagereceptionist/{garagereceptionistsid}")
    public ResponseEntity<Object> assignGarageReseptionistToCliet(@PathVariable("id") Long id, @PathVariable("garagereceptionistsid") Long garagereceptionistsid) {
        clientService.assignGarageReseptionistToCliet(id,garagereceptionistsid );
        return ResponseEntity.noContent().build();
    }

//    @PutMapping("/clients/users/{id}/{userName}")
    @PutMapping("/clients/{id}/users/{userName}")
    public ResponseEntity<Object> assignUserToCliet(@PathVariable("id") Long id, @PathVariable("userName") String userName) {
        clientService.assignUserToClient(id, userName);
        return ResponseEntity.noContent().build();
    }
}
