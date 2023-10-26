package com.devsu.account.service;

import com.devsu.account.dto.CustomException;
import com.devsu.account.entity.Cuenta;
import com.devsu.account.repository.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AccountService {

  @Autowired
  private AccountRepo accountRepo;

  public List<Cuenta> getAll() {
    return accountRepo.findAll();
  }

  public Cuenta getAccountByNumero(String numero) {
    return accountRepo.findByNumero(numero);
  }

  public List<Cuenta> getAccountsByClienteId(int clienteId) {
    return accountRepo.findByUsuarioId(clienteId);
  }

  public Cuenta createAccount(Cuenta account) throws CustomException {
    Cuenta existingAccount = getAccountByNumero(account.getNumero());
    if (existingAccount != null) {
      throw new CustomException(400, "12300");
    }
    return accountRepo.save(account);
  }

  public Cuenta updateAccount(String numero, Cuenta updatedAccount) throws CustomException {
    Cuenta existingAccount = getAccountByNumero(numero);

    if (existingAccount == null) {
      throw new CustomException(404, "12000");
    }

    existingAccount.setSaldoInicial(updatedAccount.getSaldoInicial());
    existingAccount.setEstado(updatedAccount.getEstado());
    existingAccount.setTipo(updatedAccount.getTipo());
    existingAccount.setUsuarioId(updatedAccount.getUsuarioId());

    return accountRepo.save(existingAccount);
  }

  public void deleteAccount(String numero) throws CustomException {
    Cuenta existingAccount = getAccountByNumero(numero);
    if (existingAccount == null) {
      throw new CustomException(404, "12000");
    }
    accountRepo.delete(existingAccount);
  }
}
