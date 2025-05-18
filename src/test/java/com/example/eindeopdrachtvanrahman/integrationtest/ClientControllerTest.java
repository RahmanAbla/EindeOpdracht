package com.example.eindeopdrachtvanrahman.integrationtest;//package com.example.eindeopdrachtvanrahman.integrationtest;
import com.example.eindeopdrachtvanrahman.Services.ClientService;
import com.example.eindeopdrachtvanrahman.dto.ClientDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;



@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    private ClientDTO mockClientDTO;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();

        mockClientDTO = new ClientDTO();
        mockClientDTO.setId(1L);
//        mockClientDTO.setName("John Doe");
//        mockClientDTO.setAge(30);
//        mockClientDTO.setEmail("john@example.com");
    }
    @Test
    void testAddClient() throws Exception {
        ClientDTO mockClientDTO = new ClientDTO();
        mockClientDTO.setId(1L);

        Mockito.when(clientService.addClient(Mockito.any(ClientDTO.class))).thenReturn(mockClientDTO);

        mockMvc.perform(post("/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockClientDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));
    }


    @Test
    void testGetAllClients() throws Exception {
        Mockito.when(clientService.getAllClients()).thenReturn(List.of(mockClientDTO));

        mockMvc.perform(get("/clients"))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].name").value("John Doe"));
                .andExpect(jsonPath("$[0].id").value(1));

    }

    @Test
    void shouldThrowExceptionWhenClientNotFoundById() throws Exception {
        Long invalidClientId = 999L;

        // Mock clientService om de juiste exception te gooien
        Mockito.when(clientService.getClientById(invalidClientId))
                .thenThrow(new Exception("no client found"));

        Exception thrownException = assertThrows(Exception.class, () -> {
            clientService.getClientById(invalidClientId);
        });

        assertEquals("no client found", thrownException.getMessage());
    }



}


