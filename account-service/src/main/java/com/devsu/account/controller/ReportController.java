package com.devsu.account.controller;

import com.devsu.account.dto.CustomException;
import com.devsu.account.dto.DetalleMovimiento;
import com.devsu.account.service.AccountService;
import com.devsu.account.service.MovementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reportes")
public class ReportController {
  private static final Logger LOGGER = LoggerFactory.getLogger(ReportController.class);

  @Autowired
  private AccountService accountService;

  @Autowired
  private MovementService movementService;

  @GetMapping
  public ResponseEntity<List<DetalleMovimiento>> generateReport(
                  @RequestParam("fechaInicio") String fechaInicio,
                  @RequestParam("fechaFin") String fechaFin,
                  @RequestParam("clienteId") int clienteId) throws CustomException {

      List<DetalleMovimiento> estadoCuenta = movementService.findMovementsByClienteIdAndFecha(clienteId, fechaInicio, fechaFin);

    return ResponseEntity.ok(estadoCuenta);
  }
}
