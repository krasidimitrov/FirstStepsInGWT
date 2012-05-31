package com.onlinebank.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.onlinebank.client.BankService;
import com.onlinebank.client.exception.IncorrectDataFormatException;
import com.onlinebank.client.model.User;

import java.math.BigDecimal;

public class BankServiceImpl extends RemoteServiceServlet implements BankService {

  @Override
  public void register(User user) {
    if(user.getUsername().matches("^[A-Za-z0-9]{5,20}$")){
      return;
    } else {
      throw new IncorrectDataFormatException();
    }
  }

  @Override
  public User login(String username, String password) {
   return null;
  }

  @Override
  public BigDecimal deposit(BigDecimal money) {
    return new BigDecimal(10);
  }

  @Override
  public BigDecimal withdraw(BigDecimal money) {
    return new BigDecimal(5);
  }
}