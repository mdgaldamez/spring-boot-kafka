package com.devsu.account.dto;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.IllegalFormatException;
import java.util.Map;

public class CustomException extends Exception {
  private static final Map<String, String> ERROR_CODES = new HashMap<>();

  static {
    ERROR_CODES.put("10000", "OK");
    ERROR_CODES.put("12000", "Cuenta no existe");
    ERROR_CODES.put("12100", "Saldo no disponible");
    ERROR_CODES.put("12200", "Error inesperado");
    ERROR_CODES.put("12300", "Numero de cuenta ya existe");
    ERROR_CODES.put("12400", "Cuenta no tiene movimientos");
    ERROR_CODES.put("12500", "Movimiento no existe");
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
