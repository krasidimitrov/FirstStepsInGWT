package com.onlinebank.client;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.onlinebank.client.event.GoToLoginButtonClickedEvent;
import com.onlinebank.client.event.GoToLoginButtonClickedEventHandler;
import com.onlinebank.client.event.GoToRegistrationButtonClickedEvent;
import com.onlinebank.client.event.GoToRegistrationButtonClickedEventHandler;
import com.onlinebank.client.presenter.LoginMessagesImpl;
import com.onlinebank.client.presenter.LoginPresenter;
import com.onlinebank.client.presenter.Presenter;
import com.onlinebank.client.presenter.RegisterPresenter;
import com.onlinebank.client.presenter.RegistrationMessageImpl;
import com.onlinebank.client.presenter.UserPanelPresenter;import com.onlinebank.client.view.LoginViewImpl;
import com.onlinebank.client.view.RegisterViewImpl;
import com.onlinebank.client.view.UserPanelViewImpl;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class AppController implements Presenter, ValueChangeHandler<String> {

  private final EventBus eventBus;
  private final BankServiceAsync bankRpcService;
  private HasWidgets container;
  private RegisterViewImpl registerView = null;
  private LoginViewImpl loginView = null;
  private UserPanelViewImpl userPanelView = null;
  private final RegisterServiceAsync registerRpcService;
  private final LoginServiceAsync loginRpcService;


  public AppController(BankServiceAsync bankRpcService, RegisterServiceAsync registerRpcService, LoginServiceAsync loginRpcService, EventBus eventBus) {
    this.bankRpcService = bankRpcService;
    this.registerRpcService = registerRpcService;
    this.loginRpcService = loginRpcService;
    this.eventBus = eventBus;
    bind();
  }

  private void bind() {
    History.addValueChangeHandler(this);

    eventBus.addHandler(GoToLoginButtonClickedEvent.TYPE, new GoToLoginButtonClickedEventHandler() {
      @Override
      public void onGoToLoginButtonClicked(GoToLoginButtonClickedEvent event) {
        History.newItem("login");
      }
    });

    eventBus.addHandler(GoToRegistrationButtonClickedEvent.TYPE, new GoToRegistrationButtonClickedEventHandler() {
      @Override
      public void onGoToRegistrationButtonClicked(GoToRegistrationButtonClickedEvent event) {
        History.newItem("register");
      }
    });
  }

  @Override
  public void go(HasWidgets container) {
    this.container = container;

    if ("".equals(History.getToken())) {
      History.newItem("login");
    } else {
      History.fireCurrentHistoryState();
    }
  }

  @Override
  public void onValueChange(ValueChangeEvent<String> stringValueChangeEvent) {
    String token = stringValueChangeEvent.getValue();

    if (token != null) {
      if (token.equals("register")) {

        if (registerView == null) {
          registerView = new RegisterViewImpl();
        }

        new RegisterPresenter(registerRpcService, eventBus, registerView, new RegistrationMessageImpl()).go(container);
      }
      if (token.equals("login")) {

        if (loginView == null) {
          loginView = new LoginViewImpl();
        }

         new LoginPresenter(loginRpcService, eventBus, loginView, new LoginMessagesImpl()).go(container);
        
      }

      if (token.equals("userPanel")){

        if(userPanelView == null){
          userPanelView = new UserPanelViewImpl();
        }

        new UserPanelPresenter(bankRpcService, eventBus, userPanelView).go(container);
      }
    }


  }
}
