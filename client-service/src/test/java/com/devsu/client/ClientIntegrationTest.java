package com.devsu.client;

import com.devsu.client.entity.Cliente;
import com.devsu.client.service.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ClientIntegrationTest {
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ClientService clientService;

  @Test
  public void testCreateClient() throws Exception {
    String customerJson = "{\n" +
                    "    \"Nombres\": \"John Doe\",\n" +
                    "    \"Género\": \"Masculino\",\n" +
                    "    \"Fecha Nacimiento\": \"1980-05-24\",\n" +
                    "    \"Identificación\": \"DNI\",\n" +
                    "    \"Dirección\": \"Calle Astorga 831\",\n" +
                    "    \"Teléfono\": \"5043167232\",\n" +
                    "    \"Cliente Id\": 923956789,\n" +
                    "    \"Contraseña\": \"1234\",\n" +
                    "    \"Estado\": \"True\"\n" +
                    "}";

    mockMvc.perform(MockMvcRequestBuilders
                                    .post("/clientes")
                                    .content(customerJson)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated());

    Cliente createdCustomer = clientService.getClientById(923956789);

    assertNotNull(createdCustomer);
    assertEquals("John Doe", createdCustomer.getNombre());
    assertEquals(923956789, createdCustomer.getClienteId());
    assertEquals("Masculino", createdCustomer.getGenero());
    assertEquals("5043167232", createdCustomer.getTelefono());
    assertEquals("Calle Astorga 831", createdCustomer.getDireccion());
    clientService.deleteClient(923956789);
  }

  @Test
  public void testDeleteClient() throws Exception {
    Cliente client = new Cliente();
    client.setNombre("John Doe");
    client.setClienteId(923956789);
    client.setGenero("Masculino");
    client.setTelefono("5043167232");
    client.setDireccion("Calle Astorga 831");
    client.setIdentificacion("DNI");
    client.setFechaNacimiento(new Date());
    client.setPassword("1234");
    client.setStatus("True");

    Cliente savedClient = clientService.createClient(client);

    mockMvc.perform(MockMvcRequestBuilders
                                    .delete("/clientes/{clienteId}", savedClient.getClienteId())
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());

    Cliente deletedClient = clientService.getClientById(savedClient.getClienteId());

    assertNull(deletedClient);
  }
}
