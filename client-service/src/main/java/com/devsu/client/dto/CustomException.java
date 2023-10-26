package com.devsu.client.dto;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.IllegalFormatException;
import java.util.Map;

public class CustomException extends Exception {
  private static final Map<String, String> ERROR_CODES = new HashMap<>();

  static {
    ERROR_CODES.put("10000", "OK");
    ERROR_CODES.put("12000", "Cliente Id es obligatorio");
    ERROR_CODES.put("12100", "Fecha de Nacimiento es obligatorio");
    ERROR_CODES.put("12200", "Contraseña es obligatoria");
    ERROR_CODES.put("12300", "Nombres es obligatorio");
    ERROR_CODES.put("12400", "Cliente Id ya esta registrado");
    ERROR_CODES.put("12500", "No se encontró cliente Id");
    ERROR_CODES.put("12600", "Campo con valor no valido");
  }

  private final int httpCode;
  private final String code;
  private final String message;

  public CustomException(int httpCode, String code) {
    super(code);
    this.httpCode = httpCode;
    this.code = code;
    this.message = getMessage(code);
  }

  public CustomException(int httpCode, String code, Object... params) {
    super(code);
    this.httpCode = httpCode;
    this.code = code;
    this.message = getMessage(code, params);
  }

  public static String getMessage(String code, Object... params) {
    String message = ERROR_CODES.get(code);
    if (message != null) {
      try {
        message = String.format(message, params);
      } catch (IllegalFormatException e) {
        message = ERROR_CODES.get("99998");
      }
    } else {
      message = ERROR_CODES.get("99999");
    }
    return message;
  }

  @Override
  public String getMessage() {
    return this.message;
  }

  public ResponseEntity buildGenericResponse() {

    Map<String, Object> obj = new HashMap<>();
    obj.put("code", this.code);
    obj.put("message", this.message);

    return ResponseEntity.status(this.getHttpCode())
                    .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                    .body(obj);
  }

  public int getHttpCode() {
    return httpCode;
  }

  public String getCode() {
    return code;
  }
}
