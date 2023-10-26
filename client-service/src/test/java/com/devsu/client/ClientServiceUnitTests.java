package com.devsu.client;

import com.devsu.client.dto.CustomException;
import com.devsu.client.entity.Cliente;
import com.devsu.client.repository.ClientRepo;
import com.devsu.client.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ClientServiceUnitTests {
  @InjectMocks
  private ClientService clientService;

  @Mock
  private ClientRepo clientRepo;

  @BeforeEach
  public void init() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testCreateClient() throws CustomException {

    Cliente client = new Cliente();
    client.setNombre("John Doe");
    client.setClienteId(123956789);
    client.setGenero("Masculino");
    client.setTelefono("5043167232");
    client.setDireccion("Calle Astorga 831");
    client.setIdentificacion("DNI");
    client.setFechaNacimiento(new Date());
    client.setPassword("1234");
    client.setStatus("True");


    when(clientRepo.save(any(Cliente.class))).thenReturn(client);
    when(clientService.getClientById(123956789)).thenReturn(null);
    Cliente createdClient = clientService.createClient(client);

    verify(clientRepo).save(any(Cliente.class));
    assertNotNull(createdClient);
    assertEquals("John Doe", createdClient.getNombre());
    assertEquals(123956789, createdClient.getClienteId());
  }

  @Test
  public void testGetAll() {

    List<Cliente> clientes = new ArrayList<>();

    Cliente client1 = new Cliente();
    client1.setNombre("Mario Diaz");
    client1.setClienteId(923956789);

    Cliente client2 = new Cliente();
    client2.setNombre("Jane Doe");
    client2.setClienteId(923951289);
    clientes.add(client1);
    clientes.add(client2);

    when(clientRepo.findAll()).thenReturn(clientes);

    List<Cliente> retrievedClients = clientService.getAll();

    verify(clientRepo).findAll();
    assertNotNull(retrievedClients);
    assertEquals(2, retrievedClients.size());
  }

  @Test
  public void testGetClientById() throws CustomException {

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

    when(clientRepo.findByClienteId(923956789)).thenReturn(client);

    Cliente retrievedClient = clientService.getClientById(923956789);

    verify(clientRepo).findByClienteId(923956789);

    assertNotNull(retrievedClient);
    assertEquals("John Doe", retrievedClient.getNombre());
    assertEquals(923956789, retrievedClient.getClienteId());
  }

  @Test
  public void testUpdateClient() throws CustomException {

    Cliente existingClient = new Cliente();
    existingClient.setNombre("John Doe");
    existingClient.setClienteId(923956789);
    existingClient.setFechaNacimiento(new Date());
    existingClient.setGenero("Masculino");
    existingClient.setTelefono("5043167232");
    existingClient.setDireccion("Calle Astorga 831");
    existingClient.setIdentificacion("DNI");
    existingClient.setPassword("1234");
    existingClient.setStatus("True");

    Cliente updatedClient = new Cliente();
    updatedClient.setNombre("Jane Doe");
    updatedClient.setClienteId(923956789);
    updatedClient.setFechaNacimiento(new Date());
    updatedClient.setGenero("Femenino");
    updatedClient.setTelefono("5043167232");
    updatedClient.setDireccion("Calle Astorga 831");
    updatedClient.setIdentificacion("DNI");
    updatedClient.setPassword("1234");
    updatedClient.setStatus("False");

    when(clientRepo.findByClienteId(923956789)).thenReturn(existingClient);
    when(clientRepo.save(any(Cliente.class))).thenReturn(updatedClient);

    Cliente updated = clientService.updateClient(923956789, updatedClient);

    verify(clientRepo).findByClienteId(923956789);
    verify(clientRepo).save(any(Cliente.class));

    assertNotNull(updated);
    assertEquals("Jane Doe", updated.getNombre());
    assertEquals(923956789, updated.getClienteId());
  }

  @Test
  public void testDeleteClient() throws CustomException {

    Cliente existingClient = new Cliente();
    existingClient.setNombre("John Doe");
    existingClient.setClienteId(923956789);

    when(clientRepo.findByClienteId(923956789)).thenReturn(existingClient);

    clientService.deleteClient(923956789);

    verify(clientRepo).findByClienteId(923956789);
    verify(clientRepo).delete(existingClient);
  }
}
