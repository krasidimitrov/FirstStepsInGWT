package com.onlinebank.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import java.math.BigDecimal;

public interface BankServiceAsync {

  void deposit(BigDecimal money, AsyncCallback<BigDecimal> async);

  void withdraw(BigDecimal money, AsyncCallback<BigDecimal> async);

  void isUserAuthorized(AsyncCallback<Boolean> async);
}
