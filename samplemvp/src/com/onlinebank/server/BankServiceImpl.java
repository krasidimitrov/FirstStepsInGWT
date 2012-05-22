package com.onlinebank.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.onlinebank.client.BankService;

public class BankServiceImpl extends RemoteServiceServlet implements BankService {
  // Implementation of sample interface method
  public String getMessage(String msg) {
    return "Client said: \"" + msg + "\"<br>Server answered: \"Hi!\"";
  }
}