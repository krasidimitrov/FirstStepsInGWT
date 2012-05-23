package com.onlinebank.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.onlinebank.client.model.User;

@RemoteServiceRelativePath("OnlineBankService")
public interface BankService extends RemoteService {
  void register(User user);
}
