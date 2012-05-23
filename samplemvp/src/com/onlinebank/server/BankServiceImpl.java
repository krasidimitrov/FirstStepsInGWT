package com.onlinebank.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.onlinebank.client.BankService;
import com.onlinebank.client.model.User;

public class BankServiceImpl extends RemoteServiceServlet implements BankService {

  @Override
  public void register(User user) {

  }
}