package com.devsu.account.controller;

import com.devsu.account.dto.CustomException;
import com.devsu.account.entity.Cuenta;
import com.devsu.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class AccountController {
  @Autowired
  private AccountService accountService;

  @GetMapping
  public ResponseEntity<List<Cuenta>> getAll() {
    List<Cuenta> accounts = accountService.getAll();
    return ResponseEntity.ok(accounts);
  }

  @GetMapping("/{numero}")
  public ResponseEntity<Cuenta> getAccountByNumero(@PathVariable String numero) {
    try {
      Cuenta account = accountService.getAccountByNumero(numero);
      if (account != null) {
        return ResponseEntity.ok(account);
      } else {
        throw new CustomException(404, "12500");
      }
    } catch (CustomException e) {
      return e.buildGenericResponse();
    }
  }

  @PostMapping
  public ResponseEntity<Cuenta> createAccount(@RequestBody Cuenta account) {
    try {
      Cuenta newAccount = accountService.createAccount(account);
      return ResponseEntity.status(HttpStatus.CREATED).body(newAccount);
    } catch (CustomException e) {
      return e.buildGenericResponse();
    }
  }

  @PutMapping("/{numero}")
  public ResponseEntity<Cuenta> updateAccount(@PathVariable String numero, @RequestBody Cuenta account) {
    try {
      Cuenta updatedAccount = accountService.updateAccount(numero, account);
      return ResponseEntity.ok(updatedAccount);
    } catch (CustomException e) {
      return e.buildGenericResponse();
    }
  }

  @DeleteMapping("/{numero}")
  public ResponseEntity<Void> deleteAccount(@PathVariable String numero) {
    try {
      accountService.deleteAccount(numero);
      return ResponseEntity.ok().build();
    } catch (CustomException e) {
      return e.buildGenericResponse();
    }
  }
}
