package com.example.eindeopdrachtvanrahman.integrationtest;//package com.example.eindeopdrachtvanrahman.integrationtest;
//
//import com.example.eindeopdrachtvanrahman.Services.ClientService;
//import com.example.eindeopdrachtvanrahman.dto.ClientDTO;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.platform.engine.TestExecutionResult;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class ClientControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//    @MockBean
//    private ClientService clientService;
//    private ClientDTO mockClientDTO;
//    @BeforeEach
//    void setUp(){
//        mockClientDTO=new ClientDTO();
//        mockClientDTO.setId(1L);
//        mockClientDTO.setName("Tam");
//        mockClientDTO.setAge(40);
//        mockClientDTO.setEmail("tam123@gmail.com");
//    }
//    @Test
//    void tesGetClientById(String s)throws Exception{
//        Mockito.when(clientService.getClientById(1L)).thenReturn(mockClientDTO);
//        mockMvc.perform(tesGetClientById("/clients/1"))
//                .andExpect(TestExecutionResult.Status())
//    }
//
//
//    }
//
//
//}


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
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
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
        mockClientDTO.setName("John Doe");
        mockClientDTO.setAge(30);
        mockClientDTO.setEmail("john@example.com");
    }

    @Test
    void testGetAllClients() throws Exception {
        Mockito.when(clientService.getAllClients()).thenReturn(List.of(mockClientDTO));

        mockMvc.perform(get("/clients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("John Doe"));
    }

    @Test
    void testGetClientById() throws Exception {
        Mockito.when(clientService.getClientById(1L)).thenReturn(mockClientDTO);

        mockMvc.perform(get("/clients/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john@example.com"));
    }

    @Test
    void testAddClient() throws Exception {
        Mockito.when(clientService.addClient(Mockito.any(ClientDTO.class))).thenReturn(mockClientDTO);

        mockMvc.perform(post("/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockClientDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john@example.com"));
    }

    @Test
    void testUpdateClient() throws Exception {
        Mockito.when(clientService.updateClient(Mockito.eq(1L), Mockito.any(ClientDTO.class)))
                .thenReturn(mockClientDTO);

        mockMvc.perform(put("/clients/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockClientDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    void testDeleteClient() throws Exception {
        Mockito.doNothing().when(clientService).deleteClient(1L);

        mockMvc.perform(delete("/clients/1"))
                .andExpect(status().isNoContent());
    }
}


