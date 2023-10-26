package com.devsu.client.service;

import com.devsu.client.dto.CustomException;
import com.devsu.client.entity.Cliente;
import com.devsu.client.repository.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ClientService {

  @Autowired
  private ClientRepo clientRepo;

  public List<Cliente> getAll() {
    return clientRepo.findAll();
  }

  public Cliente getClientById(int id) throws CustomException {
    Cliente client = clientRepo.findByClienteId(id);
    return client;
  }

  public Cliente createClient(Cliente client) throws CustomException {
    validateRequiredFields(client);
    Cliente existingClient = getClientById(client.getClienteId());
    if (existingClient != null) {
      throw new CustomException(400, "12400");
    }
    return clientRepo.save(client);
  }

  public Cliente updateClient(int clientId, Cliente updatedClient) throws CustomException {
    validateRequiredFields(updatedClient);
    Cliente existingClient = getClientById(clientId);

    if (existingClient == null) {
      throw new CustomException(404, "12500");
    }

    existingClient.setNombre(updatedClient.getNombre());
    existingClient.setClienteId(updatedClient.getClienteId());
    existingClient.setDireccion(updatedClient.getDireccion());
    existingClient.setFechaNacimiento(updatedClient.getFechaNacimiento());
    existingClient.setGenero(updatedClient.getGenero());
    existingClient.setIdentificacion(updatedClient.getIdentificacion());
    existingClient.setPassword(updatedClient.getPassword());
    existingClient.setStatus(updatedClient.getStatus());
    existingClient.setTelefono(updatedClient.getTelefono());

    return clientRepo.save(existingClient);
  }

  public Cliente updateClientPartial(int clienteId, Map<String, Object> camposActualizados) throws CustomException {
    try {
      Cliente cliente = getClientById(clienteId);

      if (cliente == null) {
        throw new CustomException(404, "12500");
      }

      if (camposActualizados.containsKey("Nombres")) {
        cliente.setNombre((String) camposActualizados.get("Nombres"));
      }
      if (camposActualizados.containsKey("Género")) {
        cliente.setGenero((String) camposActualizados.get("Género"));
      }
      if (camposActualizados.containsKey("Fecha Nacimiento")) {
        cliente.setFechaNacimiento((Date) camposActualizados.get("Fecha Nacimiento"));
      }
      if (camposActualizados.containsKey("Identificación")) {
        cliente.setIdentificacion((String) camposActualizados.get("Identificación"));
      }
      if (camposActualizados.containsKey("Dirección")) {
        cliente.setDireccion((String) camposActualizados.get("Dirección"));
      }
      if (camposActualizados.containsKey("Teléfono")) {
        cliente.setTelefono((String) camposActualizados.get("Teléfono"));
      }
      if (camposActualizados.containsKey("Cliente Id")) {
        cliente.setClienteId((Integer) camposActualizados.get("Cliente Id"));
      }
      if (camposActualizados.containsKey("Contraseña")) {
        cliente.setPassword((String) camposActualizados.get("Contraseña"));
      }
      if (camposActualizados.containsKey("Estado")) {
        cliente.setStatus((String) camposActualizados.get("Estado"));
      }
      return updateClient(clienteId, cliente);
    } catch(ClassCastException e) {
      throw new CustomException(400, "12600");
    }
  }

  public void deleteClient(int clientId) throws CustomException {
    Cliente existingClient = getClientById(clientId);
    if (existingClient == null) {
      throw new CustomException(404, "12500");
    }
    clientRepo.delete(existingClient);
  }

  public void validateRequiredFields(Cliente client) throws CustomException {
    if (client != null && client.getClienteId() <=0) {
      throw new CustomException(400, "12000");
    }
    if (client != null && client.getFechaNacimiento() == null) {
      throw new CustomException(400, "12100");
    }
    if (client != null && client.getPassword() == null || client.getPassword().isEmpty()) {
      throw new CustomException(400, "12200");
    }
    if (client != null && client.getNombre() == null || client.getNombre().isEmpty()) {
      throw new CustomException(400, "12300");
    }
  }
}
