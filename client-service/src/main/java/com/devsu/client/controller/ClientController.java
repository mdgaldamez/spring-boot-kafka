package com.devsu.client.controller;

import com.devsu.client.dto.CustomException;
import com.devsu.client.entity.Cliente;
import com.devsu.client.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/clientes")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<List<Cliente>> getAll() {
        List<Cliente> clients = clientService.getAll();
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<Cliente> getClientById(@PathVariable int clienteId) {
        try {
            Cliente client = clientService.getClientById(clienteId);
            if (client != null) {
                return ResponseEntity.ok(client);
            } else {
                throw new CustomException(404, "12500");
            }
        } catch (CustomException e) {
            return e.buildGenericResponse();
        }
    }

    @PostMapping
    public ResponseEntity<Cliente> createClient(@RequestBody Cliente cliente) {
        try {
            Cliente newClient = clientService.createClient(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(newClient);
        } catch (CustomException e) {
            return e.buildGenericResponse();
        }
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<Cliente> updateClient(@PathVariable int clienteId, @RequestBody Cliente cliente) {
        try {
            Cliente updatedClient = clientService.updateClient(clienteId, cliente);
            return ResponseEntity.ok(updatedClient);
        } catch (CustomException e) {
            return e.buildGenericResponse();
        }
    }

    @PatchMapping("/{clienteId}")
    public ResponseEntity<Cliente> updateClientePartially(@PathVariable int clienteId, @RequestBody Map<String, Object> camposActualizados) {
        try {
            Cliente cliente = clientService.updateClientPartial(clienteId, camposActualizados);
            return ResponseEntity.ok(cliente);
        } catch (CustomException e) {
            return e.buildGenericResponse();
        }
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> deleteClient(@PathVariable int clienteId) {
        try {
            clientService.deleteClient(clienteId);
            return ResponseEntity.ok().build();
        } catch (CustomException e) {
            return e.buildGenericResponse();
        }
    }
}
