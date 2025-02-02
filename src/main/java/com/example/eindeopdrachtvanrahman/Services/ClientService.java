package com.example.eindeopdrachtvanrahman.Services;

import com.example.eindeopdrachtvanrahman.dto.RecordNotFoundException;
import com.example.eindeopdrachtvanrahman.dto.ClientDTO;
import com.example.eindeopdrachtvanrahman.models.Client;
import com.example.eindeopdrachtvanrahman.repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<ClientDTO> getAllClients(){
        List<Client>clientList=clientRepository.findAll();
        List<ClientDTO>clientDTOList=new ArrayList<>();
        for (Client client: clientList){
            ClientDTO dto=transferToDTO(client);
            clientDTOList.add(dto);
        }
        return clientDTOList;
    }
    public ClientDTO getClientById(Long id) throws Exception {
            Optional<Client> clientOptional = clientRepository.findById(id);
        if (clientOptional.isPresent()){
            Client client1 = clientOptional.get();
            return transferToDTO(client1);
        } else {
            throw new Exception("no client found");
        }
    }

    public Client transferToClient(ClientDTO dto) {
        var client = new Client();

        client.setId(dto.getId());
        client.setAge(dto.getAge());
        client.setEmail(dto.getEmail());
        client.setName(dto.getName());
        return client;
    }
    public ClientDTO transferToDTO(Client client){
            ClientDTO dto=new ClientDTO();
        dto.setId(client.getId());
        dto.setAge(client.getAge());
        dto.setEmail(client.getEmail());
        dto.setName(client.getName());
        return dto;
    }
    public ClientDTO addClient(ClientDTO dto) {
        Client client=transferToClient(dto);
        clientRepository.save(client);
        return  transferToDTO(client);
    }
    public ClientDTO updateClient(Long id, ClientDTO clientDTO) throws RecordNotFoundException {

        if (clientRepository.findById(id).isPresent()) {

            Client client = clientRepository.findById(id).get();

            Client client1 = transferToClient(clientDTO);
            client1.setId(client.getId());

            clientRepository.save(client1);

            return transferToDTO(client1);

        } else {

            throw new RecordNotFoundException();

        }
    }
    public void deleteClient(@RequestBody Long id) {

        clientRepository.deleteById(id);

    }
}
