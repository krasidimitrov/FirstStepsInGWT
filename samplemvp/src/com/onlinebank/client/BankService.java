package com.onlinebank.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.server.rpc.XsrfProtect;

import java.math.BigDecimal;

@XsrfProtect
@RemoteServiceRelativePath("OnlineBankService")
public interface BankService extends RemoteService {

  BigDecimal deposit(BigDecimal money);

  BigDecimal withdraw(BigDecimal money);

  boolean isUserAuthorized();
}
