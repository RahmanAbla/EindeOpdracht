package com.example.eindeopdrachtvanrahman.unittests;

import com.example.eindeopdrachtvanrahman.Services.ClientService;
import com.example.eindeopdrachtvanrahman.Services.GarageReceptionistService;
import com.example.eindeopdrachtvanrahman.dto.*;
import com.example.eindeopdrachtvanrahman.models.Client;
import com.example.eindeopdrachtvanrahman.models.GarageReceptionist;
import com.example.eindeopdrachtvanrahman.repository.ClientRepository;
import com.example.eindeopdrachtvanrahman.repository.GarageReseptionistRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest<T> {
    @Mock
    ClientRepository clientRepository;

    @Mock
    GarageReseptionistRepository garageReseptionistRepository;
    @InjectMocks
    ClientService clientService;

    @Mock
    GarageReceptionistService garageReceptionistService;
    @Test
    void shouldRetrieveCorrectClientById() throws Exception {
        GarageReceptionist garageReceptionist=new GarageReceptionist("Jon",45612341,"jon456@gmail.com",4L);

        Client client1 = new Client();
        client1.setId(30l);
        client1.setName("Jan");
        client1.setAge(25);
        client1.setEmail("Jan@.hotmail.com");
        client1.setGarageReceptionist(garageReceptionist);

        GarageReceptionistDTO receptionistDTO = new GarageReceptionistDTO();
        // eventueel de eigenschappen instellen op receptionistDTO
        Mockito.when(garageReceptionistService.transferToDTO(Mockito.any(GarageReceptionist.class)))
                .thenReturn(receptionistDTO);


       Mockito.when(clientRepository.findById(30L)).thenReturn(Optional.of(client1));


       ClientDTO clientDTO = clientService.getClientById(30L);

       assertEquals(30L, clientDTO.getId());
   }

    @Test
    void shouldNotRetrieveCorrectClientById() throws Exception {

        assertThrows(com.example.eindeopdrachtvanrahman.dto.RecordNotFoundException.class,
                () -> clientService.getClientById(20L));

    }

    @Test
   void shouldRetrieveCorrectCliets() throws Exception {
        Client client = new Client(30L,"Jan",25,"Jan@.hotmail.com");
       Client client1=new Client(25L,"Tom",24,"tom234@gmail.com");
        Client client2 =new Client(24L,"Tam",23,"tam234@gmail.com");
        Client client3 =new Client(23L,"Sam",22,"sam234@gmail.com");
        Client client4=new Client(22L,"Jon",21,"jon234@gmail.com");

       List<Client> carList = new ArrayList<>();
       carList.add(client);
       carList.add(client1);
      carList.add(client2);
      carList.add(client3);

       Mockito.when(clientRepository.findAll()).thenReturn(carList);
       List<ClientDTO>clientDTOList = clientService.getAllClients();
       assertEquals(30L, clientDTOList.get(0).getId());
      assertEquals("Tom", clientDTOList.get(1).getName());
      assertEquals(23, clientDTOList.get(2).getAge());
       assertEquals("sam234@gmail.com",clientDTOList.get(3).getEmail());
    }

       @Test
   void shouldAddClientsCorrectly() throws Exception {

       ClientDTO clientDTO = new ClientDTO();
       clientDTO.setId(10L);
       clientDTO.setName("Tam");
        clientDTO.setAge(32);
        clientDTO.setEmail("tam123@gmail.com");
        ClientDTO clientDTO1=clientService.addClient(clientDTO);
        assertEquals("Tam", clientDTO1.getName());
        assertEquals("tam123@gmail.com", clientDTO1.getEmail());
        assertEquals(32, clientDTO1.getAge());
        assertEquals(10L, clientDTO1.getId());
    }

   @Test
    void shouldUpdateClientsCorrectly() throws Exception {
        Client client = new Client(30L,"Jan",25,"Jan@.hotmail.com");

       Mockito.when(clientRepository.findById(30L)).thenReturn(Optional.of(client));
       //"Tim", 20, "tim@.hotmail.com"
       ClientDTO clientDTO = new ClientDTO();
        clientDTO.setName("Tim");
       clientDTO.setAge(20);
       clientDTO.setEmail("tim@.hotmail.com");

       ClientDTO clientDTO1 = clientService.updateClient(30L, clientDTO);

       assertEquals("Tim",clientDTO1.getName());
    }
    @Test
    void shouldThrowRecordNotFoundExceptionWhenClientNotFound() {
        Long nonExistingId = 999L;
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setName("Test");
        clientDTO.setAge(30);
        clientDTO.setEmail("test@example.com");

        Mockito.when(clientRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        assertThrows(com.example.eindeopdrachtvanrahman.dto.RecordNotFoundException.class,
                () -> clientService.updateClient(nonExistingId, clientDTO));
    }
    @Test
    void shouldDeletedCorrectClientById() throws Exception {
        clientService.deleteClient(10L);
        verify(clientRepository).deleteById(10L);
    }

    @Test
    void assignGarageReseptionistToCliet() throws RecordNotFoundException {
        Client client = new Client(30L,"Jan",25,"Jan@.hotmail.com");
        GarageReceptionist garageReceptionist=new GarageReceptionist("Tam",1456961451,"Tam@123gmail.com",3L);

        Mockito.when(clientRepository.findById(30L)).thenReturn(Optional.of(client));
        Mockito.when(garageReseptionistRepository.findById(3L)).thenReturn(Optional.of(garageReceptionist));

        clientService.assignGarageReseptionistToCliet(30L, 3L);

        assertEquals(3L, client.getGarageReceptionist().getId());
    }

    @Test
    void assignGarageReseptionistNotToCliet(){

        assertThrows(com.example.eindeopdrachtvanrahman.dto.RecordNotFoundException.class,
                () -> clientService.assignGarageReseptionistToCliet(10L,20L));

    }


}


