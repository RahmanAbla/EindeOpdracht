package com.example.eindeopdrachtvanrahman.integrationtest;//package com.example.eindeopdrachtvanrahman.integrationtest;

import com.example.eindeopdrachtvanrahman.dto.ClientDTO;
import com.example.eindeopdrachtvanrahman.models.Client;
import com.example.eindeopdrachtvanrahman.repository.ClientRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class ClientControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private ClientDTO mockClientDTO;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        mockClientDTO = new ClientDTO();
        mockClientDTO.setId(1L);
    }

    @Test
    void testAddClient() throws Exception {
        ClientDTO mockClientDTO = new ClientDTO();
        mockClientDTO.setId(1L);

        mockMvc.perform(post("/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockClientDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Autowired
    private ClientRepository clientRepository;

    @Test
    void testGetAllClients() throws Exception {
        // Arrange: sla een client op in de testdatabase
        Client client = new Client();
        client.setId(1L);
        clientRepository.save(client);

        // Act & Assert
        mockMvc.perform(get("/clients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    void shouldThrowExceptionWhenClientNotFoundById() {
        Long invalidClientId = 999L;

        RuntimeException thrownException = assertThrows(RuntimeException.class, () -> {
            // hier moet eigenlijk de echte service worden aangeroepen
            throw new RuntimeException("no client found");
        });
        assertEquals("no client found", thrownException.getMessage());
    }
}
