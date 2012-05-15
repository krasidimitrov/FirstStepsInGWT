package com.uibindingexample.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UiBindServiceAsync {
  void getMessage(String msg, AsyncCallback<String> async);
}
