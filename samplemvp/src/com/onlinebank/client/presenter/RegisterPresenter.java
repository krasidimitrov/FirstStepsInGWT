package com.onlinebank.client.presenter;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.onlinebank.client.BankServiceAsync;
import com.onlinebank.client.exception.IncorrectDataFormatException;
import com.onlinebank.client.exception.UsernameAlreadyExistsException;
import com.onlinebank.client.model.User;
import com.onlinebank.client.view.RegisterView;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class RegisterPresenter implements Presenter, RegisterView.Presenter{

  private final BankServiceAsync rpcService;
  private final EventBus eventBus;
  private final RegisterView registerView;

  public RegisterPresenter(BankServiceAsync rpcService, EventBus eventBus, RegisterView registerView) {

    this.rpcService = rpcService;
    this.eventBus = eventBus;
    this.registerView = registerView;
   }

  @Override
  public void go(HasWidgets container) {
    container.clear();
    this.registerView.setPresenter(this);
    container.add((Widget) registerView);
  }

  @Override
  public void onRegisterButtonClicked() {
    registerUser();
  }

  @Override
  public void onGoToLoginButtonClicked() {
    //eventBus.fireEvent(new GoToLoginButtonClickedEvent());
    registerView.setStatusMessage("");
    History.newItem("login");
  }

  public void registerUser() {
    User user = registerView.getUser();

    rpcService.register(user, new AsyncCallback<Void>() {
      @Override
      public void onFailure(Throwable caught) {
        if(caught instanceof IncorrectDataFormatException){
        registerView.setStatusMessage("Username and password must be longer than 6 symbols");}
        else if (caught instanceof UsernameAlreadyExistsException){
          registerView.setStatusMessage("Username already exists. Try another one.");
        }
      }

      @Override
      public void onSuccess(Void result) {
        registerView.setStatusMessage("Registration Successful!");
//        registerView.redirectToMainPage();
      }
    });
  }

}
