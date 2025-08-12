package com.example.eindeopdrachtvanrahman.unittests;

import com.example.eindeopdrachtvanrahman.Services.ClientService;
import com.example.eindeopdrachtvanrahman.dto.ClientDTO;
import com.example.eindeopdrachtvanrahman.exeptions.RecordNotFoundException;
import com.example.eindeopdrachtvanrahman.models.CarMechanic;
import com.example.eindeopdrachtvanrahman.models.Client;
import com.example.eindeopdrachtvanrahman.models.User;
import com.example.eindeopdrachtvanrahman.repository.CarMechanicRepository;
import com.example.eindeopdrachtvanrahman.repository.ClientRepository;
import com.example.eindeopdrachtvanrahman.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest<T> {
    @Mock
    ClientRepository clientRepository;
    @Mock
    UserRepository userRepository;


    @Mock
    CarMechanicRepository carMechanicRepository;
    @InjectMocks
    ClientService clientService;

    @Test
    void shouldNotRetrieveCorrectClientById() throws Exception {
        assertThrows(RecordNotFoundException.class,
                () -> clientService.getClientById(20L));

    }

    @Test
    void shouldRetrieveCorrectClients() {
        Client client = new Client(30L);
        Client client1 = new Client(25L);
        Client client2 = new Client(24L);
        Client client3 = new Client(23L);
        List<Client> clientList = List.of(client, client1, client2, client3);
        Mockito.when(clientRepository.findAll()).thenReturn(clientList);
        List<ClientDTO> clientDTOList = clientService.getAllClients();
        assertEquals(30L, clientDTOList.get(0).getId());
        assertEquals(25L, clientDTOList.get(1).getId());
        assertEquals(24L, clientDTOList.get(2).getId());
        assertEquals(23L, clientDTOList.get(3).getId());
    }

    @Test
    void shouldAddClientsCorrectly() {

        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(10L);
        ClientDTO clientDTO1 = clientService.addClient(clientDTO);
        assertEquals(10L, clientDTO1.getId());
    }

    @Test
    void shouldUpdateClientsCorrectly() {
        Client client = new Client(30L);

        Mockito.when(clientRepository.findById(30L)).thenReturn(Optional.of(client));
        ClientDTO clientDTO = new ClientDTO();
        ClientDTO clientDTO1 = clientService.updateClient(30L, clientDTO);
    }

    @Test
    void shouldThrowRecordNotFoundExceptionWhenClientNotFound() {
        Long nonExistingId = 999L;
        ClientDTO clientDTO = new ClientDTO();
        Mockito.when(clientRepository.findById(nonExistingId)).thenReturn(Optional.empty());
        assertThrows(RecordNotFoundException.class,
                () -> clientService.updateClient(nonExistingId, clientDTO));
    }

    @Test
    void shouldDeletedCorrectClientById() {
        clientService.deleteClient(10L);
        verify(clientRepository).deleteById(10L);
    }

    @Test
    void assignCarMechanicToCliet() throws RecordNotFoundException {
        Client client = new Client(30L);
        CarMechanic carMechanic = new CarMechanic(3L, null, null);

        Mockito.when(clientRepository.findById(30L)).thenReturn(Optional.of(client));
        Mockito.when(carMechanicRepository.findById(3L)).thenReturn(Optional.of(carMechanic));
        clientService.assignCarMechanicToCliet(30L, 3L);
        assertEquals(3L, client.getCarMechanic().getId());
    }

    @Test
    void assignCarMechanicNotToCliet() {
        assertThrows(RecordNotFoundException.class,
                () -> clientService.assignCarMechanicToCliet(10L, 20L));

    }

    @Test
    void shouldAssignUserToClient() {
        // Arrange
        Long clientId = 1L;
        String username = "johndoe";

        Client client = new Client(clientId);
        User user = new User();
        user.setUsername(username);

        // Mocks
        Mockito.when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));
        Mockito.when(userRepository.findByUsername(username)).thenReturn(user);

        // Act
        clientService.assignUserToClient(clientId, username);

        // Assert
        assertEquals(user, client.getUser()); // controleer of user gekoppeld is
        verify(clientRepository).save(client); // controleer of opgeslagen is
    }

    @Test
    void shouldThrowRecordNotFoundWhenUserDoesNotExist() {
        Long clientId = 1L;
        String username = "unknownuser";

        Client client = new Client(clientId);

        Mockito.when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));
        Mockito.when(userRepository.findByUsername(username)).thenReturn(null); // Simuleer geen user gevonden

        assertThrows(RecordNotFoundException.class, () -> {
            clientService.assignUserToClient(clientId, username);
        });

    }

    @Test
    void shouldSetCarMechanicInDTOWhenClientHasCarMechanic() {
        // Arrange
        Client client = new Client(5L);

        CarMechanic carMechanic = new CarMechanic(3L, null, null);
        client.setCarMechanic(carMechanic);

        // Act
        ClientDTO dto = clientService.transferToDTO(client);

        // Assert
        assertNotNull(dto.getCarMechanic());
        assertEquals(3L, dto.getCarMechanic().getId());
    }

    @Test
    void shouldReturnClientDTOWhenClientExists() throws RecordNotFoundException {
        // Arrange
        Client client = new Client(10L);
        CarMechanic carMechanic = new CarMechanic(3L, null, null);
        client.setCarMechanic(carMechanic);

        Mockito.when(clientRepository.findById(10L)).thenReturn(Optional.of(client));

        // Act
        ClientDTO dto = clientService.getClientById(10L);

        // Assert
        assertNotNull(dto);
        assertEquals(10L, dto.getId());
        assertEquals(3L, dto.getCarMechanic().getId());
    }


}
