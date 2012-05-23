package com.onlinebank.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.onlinebank.client.model.User;

public interface BankServiceAsync {
  void register(User user, AsyncCallback<Void> callback);
}
