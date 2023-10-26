package com.devsu.account.service;

import com.devsu.account.dto.CustomException;
import com.devsu.account.dto.DetalleMovimiento;
import com.devsu.account.dto.MovementEvent;
import com.devsu.account.entity.Cuenta;
import com.devsu.account.entity.Movimiento;
import com.devsu.account.kafka.MovementProducer;
import com.devsu.account.repository.AccountRepo;
import com.devsu.account.repository.MovementRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class MovementService {
  private static final Logger LOGGER = LoggerFactory.getLogger(MovementService.class);

  @Autowired
  private MovementRepo movementRepo;

  @Autowired
  private AccountRepo accountRepo;

  @Autowired
  private MovementProducer movementProducer;

  public List<Movimiento> getAll() {
    return movementRepo.findAll();
  }

  public Movimiento getById(int id) {
    return movementRepo.findById(id).orElse(null);
  }

  public List<DetalleMovimiento> findMovementsByClienteIdAndFecha(int clienteId, String fechaInicio, String fechaFin) throws CustomException {
    List<DetalleMovimiento> movimientos = null;
    try {
      movimientos = movementRepo.findMovementsByClienteIdAndFecha(clienteId, fechaInicio, fechaFin);
      List<Cuenta> cuentas = accountRepo.findByClienteId(clienteId);
      SimpleDateFormat formatoOriginal = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      SimpleDateFormat formatoReporte = new SimpleDateFormat("dd/MM/yyyy");
      for (Cuenta cuenta : cuentas) {
          double saldoActual = cuenta.getSaldoInicial();
          for (DetalleMovimiento movimiento : movimientos) {
            Date fechaDate = formatoOriginal.parse(movimiento.getFecha());
            String fechaFormateada = formatoReporte.format(fechaDate);
            movimiento.setFecha(fechaFormateada);
            if ("Retiro".equals(movimiento.getTipo()) && movimiento.getValor() > 0) {
              movimiento.setValor(movimiento.getValor()*-1);
            }
            saldoActual += movimiento.getValor();
            movimiento.setSaldo_disponible(saldoActual);
          }
        }
    } catch (ParseException e) {
      throw new CustomException(400, "12200");
    }
    return movimientos;
  }

  public Movimiento create(Movimiento movement) throws CustomException {
    String accountNumber = movement.getAccount().getNumero();
    Cuenta account = accountRepo.findByNumero(accountNumber);
    movement.setAccount(account);
    movement.setDate(new Date());

    if (account == null) {
      throw new CustomException(400, "12000");
    }

    double saldo_disponible = accountRepo.calculateSaldoDisponible(accountNumber);
    if (movement.getValue() < 0 && Math.abs(movement.getValue()) > saldo_disponible) {
      throw new CustomException(400, "12100");
    }

    movement.setBalance(saldo_disponible + movement.getValue());
    movementRepo.save(movement);

    try {
      MovementEvent movementEvent = new MovementEvent();
      movementEvent.setStatus("CREATED");
      movementEvent.setMessage("Transacción realizada con exito");
      movementEvent.setMovimiento(movement);
      ObjectMapper objectMapper = new ObjectMapper();
      String movementEventAsString = objectMapper.writeValueAsString(movementEvent);

      movementProducer.sendMessage(movementEventAsString);
    } catch (JsonProcessingException e) {
      LOGGER.error(e.getLocalizedMessage(), e);
    }

    return movement;
  }

  public Movimiento update(int movementId, Movimiento updatedMovement) throws CustomException {
    Movimiento existingMovement = getById(movementId);
    if (existingMovement == null) {
      throw new CustomException(404, "12400");
    }

    existingMovement.setBalance(updatedMovement.getBalance());
    existingMovement.setType(updatedMovement.getType());
    existingMovement.setDate(updatedMovement.getDate());
    existingMovement.setValue(updatedMovement.getValue());

    return movementRepo.save(existingMovement);
  }

  public void delete(int movementId) throws CustomException {
    Movimiento existingMovement = getById(movementId);
    if (existingMovement == null) {
      throw new CustomException(404, "12400");
    }
    movementRepo.delete(existingMovement);
  }

}
