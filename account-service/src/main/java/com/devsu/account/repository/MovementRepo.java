package com.devsu.account.repository;

import com.devsu.account.dto.DetalleMovimiento;
import com.devsu.account.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovementRepo extends JpaRepository<Movimiento, Integer> {
  @Query(nativeQuery = true)
  List<DetalleMovimiento> findMovementsByClienteIdAndFecha(int clienteId, String fechaInicio, String fechaFin);

  @Query(value = "SELECT * FROM test.movimiento WHERE cuenta_id = :cuentaId", nativeQuery = true)
  List<Movimiento> findByCuentaId(int cuentaId);
}
