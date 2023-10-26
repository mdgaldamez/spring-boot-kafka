package com.devsu.client.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Calendar;
import java.util.Date;

@Inheritance
@Entity
@Table(name = "usuario")
public abstract class Persona {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "nombre")
  private String nombre;

  @Column(name = "genero")
  private String genero;

  @Column(name = "fecha_nacimiento")
  private Date fechaNacimiento;

  @Transient
  private int edad;

  @Column(name = "identificacion")
  private String identificacion;

  @Column(name = "direccion")
  private String direccion;

  @Column(name = "telefono")
  private String telefono;

  @JsonIgnore
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @JsonProperty("Nombres")
  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  @JsonProperty("Género")
  public String getGenero() {
    return genero;
  }

  public void setGenero(String genero) {
    this.genero = genero;
  }

  @JsonIgnore
  public Date getFechaNacimiento() {
    return fechaNacimiento;
  }

  @JsonProperty("Fecha Nacimiento")
  public void setFechaNacimiento(Date fechaNacimiento) {
    this.fechaNacimiento = fechaNacimiento;
  }

  @JsonProperty("Edad")
  public int getEdad() {
    if (fechaNacimiento != null) {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(fechaNacimiento);
      Calendar fechaActual = Calendar.getInstance();
      int años = fechaActual.get(Calendar.YEAR) - calendar.get(Calendar.YEAR);
      edad = años;
    }
    return edad;
  }

  public void setEdad(int edad) {
    this.edad = edad;
  }

  @JsonProperty("Identificación")
  public String getIdentificacion() {
    return identificacion;
  }

  public void setIdentificacion(String identificacion) {
    this.identificacion = identificacion;
  }

  @JsonProperty("Dirección")
  public String getDireccion() {
    return direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  @JsonProperty("Teléfono")
  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }
}
