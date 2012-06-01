package com.onlinebank.client.presenter;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.onlinebank.client.BankServiceAsync;
import com.onlinebank.client.event.GoToRegistrationButtonClickedEvent;
import com.onlinebank.client.exception.UserNotRegisteredException;
import com.onlinebank.client.exception.WrongPasswordException;
import com.onlinebank.client.model.User;
import com.onlinebank.client.view.LoginView;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class LoginPresenter implements Presenter, LoginView.Presenter {

  private final BankServiceAsync rpcService;
  private final EventBus eventBus;
  private final LoginView loginView;
  private final LoginMessages loginMessages;

  public LoginPresenter(BankServiceAsync rpcService, EventBus eventBus, LoginView loginView, LoginMessages loginMessages) {
    this.rpcService = rpcService;
    this.eventBus = eventBus;
    this.loginView = loginView;
    this.loginMessages = loginMessages;
  }

  @Override
  public void go(HasWidgets container) {
    container.clear();
    loginView.setPresenter(this);
    container.add((Widget) loginView);
  }

  @Override
  public void onGotToRegistrationButtonClicked() {
    loginView.setStatusMessage("");
    eventBus.fireEvent(new GoToRegistrationButtonClickedEvent());
  }

  @Override
  public void onLoginButtonClicked() {
    login(loginView.getUsername(), loginView.getPassword());
  }

  public void login(String username, String password) {
    rpcService.login(username, password, new AsyncCallback<User>() {
      @Override
      public void onFailure(Throwable caught) {
        if (caught instanceof UserNotRegisteredException) {
          loginView.setStatusMessage(loginMessages.usernameNotRegistered());
        }
        if (caught instanceof WrongPasswordException){
          loginView.setStatusMessage(loginMessages.wrongPassword());
        }
      }

      @Override
      public void onSuccess(User result) {
        loginView.goToUserPage();
      }
    });
  }
}
