package com.devsu.client.repository;

import com.devsu.client.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepo extends JpaRepository<Cliente, Integer> {
  Cliente findByClienteId(int clientId);
}
