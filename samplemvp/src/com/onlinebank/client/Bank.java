package com.onlinebank.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.HasRpcToken;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.rpc.XsrfToken;
import com.google.gwt.user.client.rpc.XsrfTokenService;
import com.google.gwt.user.client.rpc.XsrfTokenServiceAsync;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class Bank implements EntryPoint {


  public void onModuleLoad() {


    XsrfTokenServiceAsync xsrf = (XsrfTokenServiceAsync)GWT.create(XsrfTokenService.class);
    ((ServiceDefTarget)xsrf).setServiceEntryPoint(GWT.getModuleBaseURL() + "xsrf");
    xsrf.getNewXsrfToken(new AsyncCallback<XsrfToken>() {
      @Override
      public void onFailure(Throwable caught) {
        //To change body of implemented methods use File | Settings | File Templates.
        int f = 0;
      }

      @Override
      public void onSuccess(XsrfToken result) {
        int i = 9;
        final BankServiceAsync rpcService = (BankServiceAsync)GWT.create(BankService.class);
        final RegisterServiceAsync registerRpcService = (RegisterServiceAsync)GWT.create(RegisterService.class);
        final LoginServiceAsync loginRpcService = (LoginServiceAsync)GWT.create(LoginService.class);

//        Cookies.setCookie("JSESSIONID" , result.getSid());

        ((HasRpcToken) loginRpcService).setRpcToken(result);
        ((HasRpcToken) rpcService).setRpcToken(result);
        ((HasRpcToken) registerRpcService).setRpcToken(result);


        EventBus eventBus = new SimpleEventBus();
        AppController appViewer = new AppController(rpcService, registerRpcService, loginRpcService, eventBus);
        appViewer.go(RootPanel.get());
      }
    });



  }


}
