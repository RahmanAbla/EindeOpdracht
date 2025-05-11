package com.example.eindeopdrachtvanrahman.sequence_diagram.Services;

import com.example.eindeopdrachtvanrahman.models.Client;
import com.example.eindeopdrachtvanrahman.dto.RecordNotFoundException;
import com.example.eindeopdrachtvanrahman.dto.ClientDTO;
import com.example.eindeopdrachtvanrahman.models.User;
import com.example.eindeopdrachtvanrahman.repository.ClientRepository;
import com.example.eindeopdrachtvanrahman.repository.GarageReseptionistRepository;
import com.example.eindeopdrachtvanrahman.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final GarageReseptionistRepository garageReseptionistRepository;
    private final GarageReceptionistService garageReceptionistService;
    private final UserRepository userRepository;

    public ClientService(ClientRepository clientRepository, GarageReseptionistRepository
            garageReseptionistRepository, GarageReceptionistService garageReceptionistService, UserRepository userRepository) {
        this.clientRepository = clientRepository;
        this.garageReseptionistRepository = garageReseptionistRepository;
        this.garageReceptionistService = garageReceptionistService;
        this.userRepository = userRepository;
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
            ClientDTO dto=transferToDTO(client1);
            if(client1.getGarageReceptionist()!=null){
                dto.setGarageReceptionistDTO(garageReceptionistService.transferToDTO(client1.getGarageReceptionist()));

            }
            return transferToDTO(client1);
        } else {
            throw new RecordNotFoundException();
        }
    }

    public Client transferToClient(ClientDTO dto) {
        var client = new Client();

        client.setId(dto.getId());
        return client;
    }
    public ClientDTO transferToDTO(Client client){
        ClientDTO dto=new ClientDTO();
        dto.setId(client.getId());
        if (client.getGarageReceptionist()!=null){

            dto.setGarageReceptionistDTO(garageReceptionistService.transferToDTO(client.getGarageReceptionist()));

        }
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

    public void assignGarageReseptionistToCliet(Long id, Long garageReseptionistId) throws RecordNotFoundException {
        var optionalClient = clientRepository.findById(id);
        var optionalGarageReseptionist = garageReseptionistRepository.findById(garageReseptionistId);

        if(optionalClient.isPresent() && optionalGarageReseptionist.isPresent()) {
            var client= optionalClient.get();
            var garageReseptionist = optionalGarageReseptionist.get();

            client.setGarageReceptionist(garageReseptionist);
            clientRepository.save(client);
        } else {
            throw new RecordNotFoundException();
        }
    }
    public void assignUserToClient(Long id,String username) throws RecordNotFoundException {
        Optional<Client> client = clientRepository.findById(id);
        Optional<User> nameOfUser = Optional.ofNullable(userRepository.findByUsername(username));

        if (nameOfUser.isPresent()){
            Client client1 = client.get();
            User user = nameOfUser.get();
            client1.setUser(user);
            clientRepository.save(client1);

        } else {
            throw new RecordNotFoundException();
        }



    }

}


//oude code
//@Service
//public class ClientService {
//    private final ClientRepository clientRepository;
//    private GarageReseptionistRepository garageReseptionistRepository;
//    private  GarageReceptionistService garageReceptionistService;
//
//    public ClientService(ClientRepository clientRepository, GarageReseptionistRepository
//            garageReseptionistRepository, GarageReceptionistService garageReceptionistService) {
//        this.clientRepository = clientRepository;
//        this.garageReseptionistRepository = garageReseptionistRepository;
//        this.garageReceptionistService = garageReceptionistService;
//    }
//
//    public List<ClientDTO> getAllClients(){
//        List<Client>clientList=clientRepository.findAll();
//        List<ClientDTO>clientDTOList=new ArrayList<>();
//        for (Client client: clientList){
//            ClientDTO dto=transferToDTO(client);
//            clientDTOList.add(dto);
//        }
//        return clientDTOList;
//    }
//    public ClientDTO getClientById(Long id) throws Exception {
//        Optional<Client> clientOptional = clientRepository.findById(id);
//        if (clientOptional.isPresent()){
//            Client client1 = clientOptional.get();
//            ClientDTO dto=transferToDTO(client1);
//            if(client1.getGarageReceptionist()!=null){
//                dto.setGarageReceptionistDTO(garageReceptionistService.transferToDTO(client1.getGarageReceptionist()));
//
//            }
//            return transferToDTO(client1);
//        } else {
//            throw new RecordNotFoundException();
//        }
//    }
//
//    public Client transferToClient(ClientDTO dto) {
//        var client = new Client();
//
//        client.setId(dto.getId());
//      client.setAge(dto.getAge());
//       client.setEmail(dto.getEmail());
//        client.setName(dto.getName());
//        return client;
//    }
//    public ClientDTO transferToDTO(Client client){
//        ClientDTO dto=new ClientDTO();
//        dto.setId(client.getId());
//      dto.setAge(client.getAge());
//       dto.setEmail(client.getEmail());
//        dto.setName(client.getName());
//        if (client.getGarageReceptionist()!=null){
//
//         dto.setGarageReceptionistDTO(garageReceptionistService.transferToDTO(client.getGarageReceptionist()));
//
//        }
//        return dto;
//    }
//    public ClientDTO addClient(ClientDTO dto) {
//        Client client=transferToClient(dto);
//        clientRepository.save(client);
//        return  transferToDTO(client);
//    }
//    public ClientDTO updateClient(Long id, ClientDTO clientDTO) throws RecordNotFoundException {
//
//        if (clientRepository.findById(id).isPresent()) {
//
//            Client client = clientRepository.findById(id).get();
//
//            Client client1 = transferToClient(clientDTO);
//            client1.setId(client.getId());
//
//
//            clientRepository.save(client1);
//
//            return transferToDTO(client1);
//
//        } else {
//
//            throw new RecordNotFoundException();
//        }
//    }
//    public void deleteClient(@RequestBody Long id) {
//
//        clientRepository.deleteById(id);
//
//    }
//
//    public void assignGarageReseptionistToCliet(Long id, Long garageReseptionistId) throws RecordNotFoundException {
//        var optionalClient = clientRepository.findById(id);
//        var optionalGarageReseptionist = garageReseptionistRepository.findById(garageReseptionistId);
//
//        if(optionalClient.isPresent() && optionalGarageReseptionist.isPresent()) {
//            var client= optionalClient.get();
//            var garageReseptionist = optionalGarageReseptionist.get();
//
//            client.setGarageReceptionist(garageReseptionist);
//            clientRepository.save(client);
//        } else {
//            throw new RecordNotFoundException();
//        }
//    }
//
//}