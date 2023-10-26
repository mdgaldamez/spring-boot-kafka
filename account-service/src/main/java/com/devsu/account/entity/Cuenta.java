package com.devsu.account.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "cuenta")
public class Cuenta {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonIgnore
  @Column(name = "id")
  private int id;

  @Column(name = "numero")
  private String numero;

  @Column(name = "tipo")
  private String tipo;

  @Column(name = "saldo_inicial")
  private double saldoInicial;

  @Column(name = "estado")
  private String estado;

  @Column(name = "usuario_id")
  private int usuarioId;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNumero() {
    return numero;
  }

  public void setNumero(String numero) {
    this.numero = numero;
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  public double getSaldoInicial() {
    return saldoInicial;
  }

  public void setSaldoInicial(double saldoInicial) {
    this.saldoInicial = saldoInicial;
  }

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  public int getUsuarioId() {
    return usuarioId;
  }

  public void setUsuarioId(int usuarioId) {
    this.usuarioId = usuarioId;
  }
}
