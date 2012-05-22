package com.onlinebank.client;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.onlinebank.client.event.GoToLoginButtonClickedEvent;
import com.onlinebank.client.event.GoToLoginButtonClickedEventHandler;
import com.onlinebank.client.presenter.Presenter;
import com.onlinebank.client.presenter.RegisterPresenter;
import com.onlinebank.client.view.LoginViewImpl;
import com.onlinebank.client.view.RegisterViewImpl;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class AppController implements Presenter, ValueChangeHandler<String>{

  private final HandlerManager eventBus;
  private final BankServiceAsync rpcService;
  private HasWidgets container;
  private RegisterViewImpl registerView = null;
  private LoginViewImpl loginView = null;

  public AppController(BankServiceAsync rpcService, HandlerManager eventBus){

    this.rpcService = rpcService;
    this.eventBus = eventBus;
    bind();
  }

  private void bind(){
    History.addValueChangeHandler(this);

    eventBus.addHandler(GoToLoginButtonClickedEvent.TYPE , new GoToLoginButtonClickedEventHandler() {
      @Override
      public void onGoToLoginButtonClicked(GoToLoginButtonClickedEvent event) {
        History.newItem("login");
      }
    });
  }

  @Override
  public void go(HasWidgets container) {
    this.container = container;

    if ("".equals(History.getToken())) {
      History.newItem("register");
    }
    else {
      History.fireCurrentHistoryState();
    }
  }

  @Override
  public void onValueChange(ValueChangeEvent<String> stringValueChangeEvent) {
    String token = stringValueChangeEvent.getValue();

    if (token != null) {
      if(token.equals("register")) {

        if (registerView == null) {
          registerView = new RegisterViewImpl();
        }

        new RegisterPresenter(eventBus, registerView).go(container);
      }
    }



  }
}
