package com.devsu.account.entity;

import com.devsu.account.dto.DetalleMovimiento;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@NamedNativeQuery(name = "Movimiento.findMovementsByClienteIdAndFecha",
                  query = "SELECT m.fecha," +
                          " u.nombre," +
                          "    c.numero," +
                          "    m.tipo," +
                          "    c.saldo_inicial," +
                          "    c.estado," +
                          "    m.valor " +
                          "FROM movimiento m JOIN cuenta c ON m.cuenta_id = c.id JOIN usuario u ON c.usuario_id = u.id " +
                          "WHERE u.cliente_id = ?1 AND m.fecha >= ?2 AND m.fecha <= ?3 ORDER BY fecha",
                  resultSetMapping = "Mapping.DetalleMovimiento")
@SqlResultSetMapping(name = "Mapping.DetalleMovimiento",
                classes = @ConstructorResult(targetClass = DetalleMovimiento.class,
                columns = { @ColumnResult(name = "fecha", type = String.class),
                            @ColumnResult(name = "nombre", type = String.class),
                            @ColumnResult(name = "numero", type = String.class),
                            @ColumnResult(name = "tipo", type = String.class),
                            @ColumnResult(name = "saldo_inicial", type = Double.class),
                            @ColumnResult(name = "estado", type = String.class),
                            @ColumnResult(name = "valor", type = Double.class)}))
@Table(name = "movimiento")
public class Movimiento {
  @Id
  @JsonIgnore
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "fecha")
  private Date date;

  @Column(name = "tipo")
  private String type;

  @Column(name = "valor")
  private double value;

  @Column(name = "saldo")
  private double balance;

  @ManyToOne
  @JoinColumn(name = "cuenta_id", referencedColumnName = "id")
  private Cuenta account;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public double getValue() {
    return value;
  }

  public void setValue(double value) {
    this.value = value;
  }

  public double getBalance() {
    return balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public Cuenta getAccount() {
    return account;
  }

  public void setAccount(Cuenta account) {
    this.account = account;
  }
}
