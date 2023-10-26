package com.devsu.account.repository;

import com.devsu.account.entity.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepo extends JpaRepository<Cuenta, Integer> {
  Cuenta findByNumero(String numero);

  @Query(value = "SELECT (c.saldo_inicial + COALESCE(SUM(m.valor), 0)) AS saldo_disponible FROM cuenta c " +
          "LEFT JOIN movimiento m ON c.id = m.cuenta_id WHERE c.numero = :numero", nativeQuery = true)
  Double calculateSaldoDisponible(@Param("numero") String numero);

  List<Cuenta> findByUsuarioId(int usuarioId);

  @Query(value = "SELECT c.* FROM test.cuenta c JOIN usuario u ON c.usuario_id = u.id WHERE u.cliente_id = " +
                  ":clienteId", nativeQuery = true)
  List<Cuenta> findByClienteId(@Param("clienteId") int clienteId);
}
