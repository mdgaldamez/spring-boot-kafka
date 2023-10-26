package com.devsu.account.dto;

import com.devsu.account.entity.Movimiento;

public class MovementEvent {
  private String message;
  private String status;
  private Movimiento movement;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Movimiento getMovimiento() {
    return movement;
  }

  public void setMovimiento(Movimiento movement) {
    this.movement = movement;
  }
}
