package com.onlinebank.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.onlinebank.client.exception.IncorrectDataFormatException;
import com.onlinebank.client.model.User;

import java.math.BigDecimal;

@RemoteServiceRelativePath("OnlineBankService")
public interface BankService extends RemoteService {
  void register(User user) throws IncorrectDataFormatException;

  User login(String username, String password);

  BigDecimal deposit(BigDecimal money);

  BigDecimal withdraw(BigDecimal money);
}
