package com.uibindingexample.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("UiBindService")
public interface UiBindService extends RemoteService {
  // Sample interface method of remote interface
  String getMessage(String msg);

  /**
   * Utility/Convenience class.
   * Use UiBindService.App.getInstance() to access static instance of UiBindServiceAsync
   */
  public static class App {
    private static UiBindServiceAsync ourInstance = GWT.create(UiBindService.class);

    public static synchronized UiBindServiceAsync getInstance() {
      return ourInstance;
    }
  }
}
