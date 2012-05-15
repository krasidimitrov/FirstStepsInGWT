package com.mySampleApplication.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class MySampleApplication implements EntryPoint {

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    LoginFormView loginFormView = new LoginFormView();
//    RegisterFormView loginFormView = new RegisterFormView();
    RootPanel.get().add(loginFormView);
  }


}
