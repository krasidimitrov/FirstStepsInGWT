package com.onlinebank.server;

import com.google.gwt.user.server.rpc.XsrfProtectedServiceServlet;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.onlinebank.client.BankService;
import com.onlinebank.client.exception.InsufficientBalanceException;
import com.onlinebank.client.model.User;

import java.math.BigDecimal;

@Singleton
public class BankServiceImpl extends XsrfProtectedServiceServlet implements BankService {

  @Inject(optional = true)
  private AccountRepository accountRepository;
  @Inject(optional = true)
  private UsersOnlineRepository usersOnlineRepository;
  @Inject(optional = true)
  private UserRepository userRepository;
//  private int accId = 1;

  public BankServiceImpl() {

  }

  public BankServiceImpl(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  @Override
  public boolean isUserAuthorized() {
    return isSidValid();
  }

  private boolean isSidValid() {
    String currentSid = getThreadLocalRequest().getSession().getId();
    if (("").equals(usersOnlineRepository.getSid(currentSid))) {
      return false;
    }
    return true;
  }

  private int getAccountId() {
    String currentSid = getThreadLocalRequest().getSession().getId();
    User user = userRepository.getUser(usersOnlineRepository.getUsername(currentSid));
    return accountRepository.getAccountId(user.getUserId());
  }


  @InTransaction
  public BigDecimal deposit(BigDecimal amount) {
    if (isSidValid()) {
      int accId = getAccountId();
      BigDecimal currentBalance = accountRepository.getBalance(accId);
      BigDecimal newBalance = currentBalance.add(amount);
      accountRepository.updateBalance(accId, newBalance);

      return newBalance;
    }
    return null;
  }

  @InTransaction
  public BigDecimal withdraw(BigDecimal amount) {

    if (!isSidValid()) {
      return null;
    }
    int accId = getAccountId();

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