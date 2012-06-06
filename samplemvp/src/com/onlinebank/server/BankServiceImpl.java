package com.onlinebank.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.onlinebank.client.BankService;
import com.onlinebank.client.exception.InsufficientBalanceException;

import java.math.BigDecimal;

@Singleton
public class BankServiceImpl extends RemoteServiceServlet implements BankService {

  @Inject
  private AccountRepository accountRepository;
  private int accId = 1;

  public BankServiceImpl(){

  }

  public BankServiceImpl(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  public BigDecimal deposit(BigDecimal amount) {
    BigDecimal currentBalance = accountRepository.getBalance(accId);
    BigDecimal newBalance = currentBalance.add(amount);
    accountRepository.updateBalance(accId, newBalance);

    return newBalance;
  }

  public BigDecimal withdraw(BigDecimal amount) {

    BigDecimal currentBalance = accountRepository.getBalance(accId);
    BigDecimal newBalance = currentBalance.subtract(amount);
    if (newBalance.compareTo(BigDecimal.ZERO) == -1) {
      throw new InsufficientBalanceException();
    } else {
      accountRepository.updateBalance(accId, newBalance);
      return newBalance;
    }
  }
}