package com.devsu.client.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

import java.util.Calendar;

@Entity
public class Cliente extends Persona {

  @Column(name = "cliente_id")
  private int clienteId;

  @Column(name = "contrasena")
  private String password;

  @Column(name = "estado")
  private String status;

  @JsonProperty("Cliente Id")
  public int getClienteId() {
    return clienteId;
  }

  public void setClienteId(int clienteId) {
    this.clienteId = clienteId;
  }

  @JsonProperty("Contraseña")
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @JsonProperty("Estado")
  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

}
