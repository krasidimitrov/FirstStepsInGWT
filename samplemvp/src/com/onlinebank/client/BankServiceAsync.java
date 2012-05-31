package com.onlinebank.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.onlinebank.client.model.User;

import java.math.BigDecimal;

public interface BankServiceAsync {
  void register(User user, AsyncCallback<Void> callback);

  void login(String username, String password, AsyncCallback<User> callback);

  void deposit(BigDecimal money, AsyncCallback<BigDecimal> async);

  void withdraw(BigDecimal money, AsyncCallback<BigDecimal> async);
}
