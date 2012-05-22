package com.onlinebank.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface BankServiceAsync {
  void getMessage(String msg, AsyncCallback<String> async);
}
