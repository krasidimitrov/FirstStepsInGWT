package com.onlinebank.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.onlinebank.client.view.LoginFormView;
import com.onlinebank.client.view.View;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class MySampleApplication implements EntryPoint {

  /**
   * This is the entry point method.
   * @param newView
   */
 // private static View view;
  public static void setView(View newView){
    RootPanel.get().remove(0);
   RootPanel.get().add(newView);

  }

  public void onModuleLoad() {
     View view = new LoginFormView();
//    RegisterFormView view = new RegisterFormView();
//    ?UserPanelView view = new UserPanelView();
    RootPanel.get().add(view);
  }


}
