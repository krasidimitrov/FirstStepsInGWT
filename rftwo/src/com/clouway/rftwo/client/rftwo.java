package com.clouway.rftwo.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class rftwo implements EntryPoint {

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    MainViewImpl mainView = new MainViewImpl();
    RootPanel.get().add(mainView);
  }
}
