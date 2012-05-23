package com.onlinebank.client.presenter;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.onlinebank.client.BankServiceAsync;
import com.onlinebank.client.event.GoToRegistrationButtonClickedEvent;
import com.onlinebank.client.view.LoginView;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class LoginPresenter implements Presenter, LoginView.Presenter{

  private final BankServiceAsync rpcService;
  private final EventBus eventBus;
  private final LoginView loginView;

  public LoginPresenter(BankServiceAsync rpcService, EventBus eventBus, LoginView loginView) {
    this.rpcService = rpcService;
    this.eventBus = eventBus;
    this.loginView = loginView;
    this.loginView.setPresenter(this);
  }

  @Override
  public void go(HasWidgets container) {
    container.clear();
    container.add((Widget) loginView);  }

  @Override
  public void onGotToRegistrationButtonClicked() {
    eventBus.fireEvent(new GoToRegistrationButtonClickedEvent());
  }

  @Override
  public void onLoginButtonClicked() {
    //To change body of implemented methods use File | Settings | File Templates.
  }
}
