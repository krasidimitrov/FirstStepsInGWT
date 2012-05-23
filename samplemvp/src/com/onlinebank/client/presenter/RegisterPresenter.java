package com.onlinebank.client.presenter;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.onlinebank.client.BankServiceAsync;
import com.onlinebank.client.event.GoToLoginButtonClickedEvent;
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
//    this.registerView.setPresenter(this);
  }

  @Override
  public void go(HasWidgets container) {
    container.clear();
    container.add((Widget) registerView);
  }

  @Override
  public void onRegisterButtonClicked() {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void onGoToLoginButtonClicked() {
    eventBus.fireEvent(new GoToLoginButtonClickedEvent());
  }

  public void registerUser() {
    User user = registerView.getUser();
    rpcService.register(user,new AsyncCallback<Void>() {
      @Override
      public void onFailure(Throwable caught) {
        //To change body of implemented methods use File | Settings | File Templates.
      }

      @Override
      public void onSuccess(Void result) {
        //To change body of implemented methods use File | Settings | File Templates.
      }
    });
  }
}
