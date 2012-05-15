package com.uibindingexample.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class UiBind implements EntryPoint {

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    MyBinderWidget myBinderWidget = new MyBinderWidget();
    RootPanel.get().add(myBinderWidget);
  }

}
