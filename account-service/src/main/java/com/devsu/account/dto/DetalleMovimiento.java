package com.devsu.account.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DetalleMovimiento {
  private String fecha;
  private String nombre;
  private String numero;
  private String tipo;
  private Double saldo_inicial;
  private String estado;
  private Double valor;
  private Double saldo_disponible;

  @JsonProperty("Fecha")
  public String getFecha() {
    return fecha;
  }

  public void setFecha(String fecha) {
    this.fecha = fecha;
  }

  @JsonProperty("Cliente")
  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  @JsonProperty("Numero Cuenta")
  public String getNumero() {
    return numero;
  }

  public void setNumero(String numero) {
    this.numero = numero;
  }

  @JsonProperty("Tipo")
  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  @JsonProperty("Saldo Inicial")
  public Double getSaldo_inicial() {
    return saldo_inicial;
  }

  public void setSaldo_inicial(Double saldo_inicial) {
    this.saldo_inicial = saldo_inicial;
  }

  @JsonProperty("Estado")
  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  @JsonProperty("Movimiento")
  public double getValor() {
    return valor;
  }

  public void setValor(double valor) {
    this.valor = valor;
  }

  @JsonProperty("Saldo Disponible")
  public double getSaldo_disponible() {
    return saldo_disponible;
  }

  public void setSaldo_disponible(double saldo_disponible) {
    this.saldo_disponible = saldo_disponible;
  }
}
