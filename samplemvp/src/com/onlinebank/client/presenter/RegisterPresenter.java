package com.onlinebank.client.presenter;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.onlinebank.client.BankServiceAsync;
import com.onlinebank.client.event.GoToLoginButtonClickedEvent;
import com.onlinebank.client.exception.IncorrectDataFormatException;
import com.onlinebank.client.exception.UsernameAlreadyExistsException;
import com.onlinebank.client.model.User;
import com.onlinebank.client.view.RegisterView;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class RegisterPresenter implements Presenter, RegisterView.Presenter {

  private final BankServiceAsync rpcService;
  private final EventBus eventBus;
  private final RegisterView registerView;
  private final RegistrationMessages registrationMessages;

  public RegisterPresenter(BankServiceAsync rpcService, EventBus eventBus, RegisterView registerView, RegistrationMessages registrationMessages) {

    this.rpcService = rpcService;
    this.eventBus = eventBus;
    this.registerView = registerView;
    this.registrationMessages = registrationMessages;
  }

  @Override
  public void go(HasWidgets container) {
    container.clear();
    registerView.setPresenter(this);
    container.add((Widget) registerView);
  }

  @Override
  public void onRegisterButtonClicked() {
    registerUser();
  }

  @Override
  public void onGoToLoginButtonClicked() {
    eventBus.fireEvent(new GoToLoginButtonClickedEvent());
    registerView.setStatusMessage("");
  }

  public void registerUser() {
    User user = registerView.getUser();
    if (!user.getUsername().matches("^[A-Za-z0-9]{5,20}$") || !user.getPassword().matches("^[A-Za-z0-9]{5,20}$")) {
      registerView.setStatusMessage(registrationMessages.WrongUsernameOrPasswordFormat());
      return;
    }

    rpcService.register(user, new AsyncCallback<Void>() {
      @Override
      public void onFailure(Throwable caught) {
        if (caught instanceof IncorrectDataFormatException) {
          registerView.setStatusMessage(registrationMessages.WrongUsernameOrPasswordFormat());
        } else if (caught instanceof UsernameAlreadyExistsException) {
          registerView.setStatusMessage(registrationMessages.usernameAlreadyExists());
        }
      }

      @Override
      public void onSuccess(Void result) {

        registerView.setStatusMessage(registrationMessages.registrationSuccessful());
      }

    });
  }

}
