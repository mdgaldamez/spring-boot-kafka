package com.devsu.account.repository;

import com.devsu.account.dto.DetalleMovimiento;
import com.devsu.account.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovementRepo extends JpaRepository<Movimiento, Integer> {
  @Query(nativeQuery = true)
  List<DetalleMovimiento> findMovementsByClienteIdAndFecha(int clienteId, String fechaInicio, String fechaFin);
}
