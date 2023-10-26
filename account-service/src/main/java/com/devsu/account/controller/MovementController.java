package com.devsu.account.controller;

import com.devsu.account.dto.CustomException;
import com.devsu.account.entity.Movimiento;
import com.devsu.account.service.MovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class MovementController {

  @Autowired
  private MovementService movementService;

  @GetMapping
  public ResponseEntity<List<Movimiento>> getAll() {
    List<Movimiento> movements = movementService.getAll();
    return ResponseEntity.ok(movements);
  }

  @GetMapping("/{numeroCuenta}")
  public ResponseEntity<List<Movimiento>> getByNumeroCuenta(@PathVariable String numeroCuenta) {
    try {
      List<Movimiento> movements = movementService.getByNumeroCuenta(numeroCuenta);
      if (movements != null && !movements.isEmpty()) {
        return ResponseEntity.ok(movements);
      } else {
        throw new CustomException(404, "12400");
      }
    } catch (CustomException e) {
      return e.buildGenericResponse();
    }
  }

  @PostMapping
  public ResponseEntity<Movimiento> create(@RequestBody Movimiento movement) {
    try {
      Movimiento movimiento = movementService.create(movement);
      return ResponseEntity.status(HttpStatus.CREATED).body(movimiento);
    } catch (CustomException e) {
      return e.buildGenericResponse();
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Movimiento> update(@PathVariable int id, @RequestBody Movimiento movement) {
    try {
      Movimiento updatedMovement = movementService.update(id, movement);
      return ResponseEntity.ok(updatedMovement);
    } catch (CustomException e) {
      return e.buildGenericResponse();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable int id) {
    try {
      movementService.delete(id);
      return ResponseEntity.ok().build();
    } catch (CustomException e) {
      return e.buildGenericResponse();
    }
  }
}
