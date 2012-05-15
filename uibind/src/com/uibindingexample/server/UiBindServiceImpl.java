package com.uibindingexample.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.uibindingexample.client.UiBindService;

public class UiBindServiceImpl extends RemoteServiceServlet implements UiBindService {
  // Implementation of sample interface method
  public String getMessage(String msg) {
    return "Client said: \"" + msg + "\"<br>Server answered: \"Hi!\"";
  }
}