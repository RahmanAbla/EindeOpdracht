package com.example.eindeopdrachtvanrahman.Services;

import com.example.eindeopdrachtvanrahman.dto.ClientDTO;
import com.example.eindeopdrachtvanrahman.exeptions.RecordNotFoundException;
import com.example.eindeopdrachtvanrahman.models.Client;
import com.example.eindeopdrachtvanrahman.models.User;
import com.example.eindeopdrachtvanrahman.repository.CarMechanicRepository;
import com.example.eindeopdrachtvanrahman.repository.ClientRepository;
import com.example.eindeopdrachtvanrahman.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final CarMechanicRepository carMechanicRepository;
    private final CarMechanicService carMechanicService;
    private final UserRepository userRepository;

    public ClientService(ClientRepository clientRepository, CarMechanicRepository carMechanicRepository, CarMechanicService carMechanicService, UserRepository userRepository) {
        this.clientRepository = clientRepository;
        this.carMechanicRepository = carMechanicRepository;
        this.carMechanicService = carMechanicService;
        this.userRepository = userRepository;
    }

    public List<ClientDTO> getAllClients() {
        List<Client> clientList = clientRepository.findAll();
        List<ClientDTO> clientDTOList = new ArrayList<>();
        for (Client client : clientList) {
            ClientDTO dto = transferToDTO(client);
            clientDTOList.add(dto);
        }
        return clientDTOList;
    }

    public ClientDTO getClientById(Long id) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if (clientOptional.isPresent()) {
            Client client1 = clientOptional.get();
            return transferToDTO(client1);
        } else {
            throw new RecordNotFoundException();
        }
    }

    public Client transferToClient(ClientDTO dto) {
        var client = new Client();
        client.setId(dto.getId());
        client.setCarMechanic(dto.getCarMechanic());
        return client;
    }

    public ClientDTO transferToDTO(Client client) {
        ClientDTO dto = new ClientDTO();
        dto.setId(client.getId());
        if (client.getCarMechanic() != null) {
            dto.setCarMechanic(client.getCarMechanic());
        }
        return dto;
    }

    public ClientDTO addClient(ClientDTO dto) {
        Client client = transferToClient(dto);
        clientRepository.save(client);
        return transferToDTO(client);
    }

    public ClientDTO updateClient(Long id, ClientDTO clientDTO) {

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

    public void assignCarMechanicToCliet(Long id, Long carMechanicId) {
        var optionalClient = clientRepository.findById(id);
        var optionalCarMechanic = carMechanicRepository.findById(carMechanicId);

        if (optionalClient.isPresent() && optionalCarMechanic.isPresent()) {
            var client = optionalClient.get();
            var carMechanic = optionalCarMechanic.get();

            client.setCarMechanic(carMechanic);
            clientRepository.save(client);
        } else {
            throw new RecordNotFoundException();
        }
    }


    public void assignUserToClient(Long id, String username) {
        var OptionalClient = clientRepository.findById(id);
        var OptionalUser = Optional.ofNullable(userRepository.findByUsername(username));
        if (OptionalClient.isPresent() && OptionalUser.isPresent()) {
            var client1 = OptionalClient.get();
            User user = OptionalUser.get();
            client1.setUser(user);
            clientRepository.save(client1);
        } else {
            throw new RecordNotFoundException();
        }


    }
}
