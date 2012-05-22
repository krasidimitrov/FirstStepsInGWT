package com.onlinebank.client.presenter;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.onlinebank.client.event.GoToLoginButtonClickedEvent;
import com.onlinebank.client.view.RegisterView;
import com.onlinebank.client.view.RegisterViewImpl;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class RegisterPresenter implements Presenter, RegisterView.Presenter{

  private final HandlerManager eventBus;
  private final RegisterViewImpl registerView;

  public RegisterPresenter(HandlerManager eventBus, RegisterViewImpl registerView) {

    this.eventBus = eventBus;
    this.registerView = registerView;
    this.registerView.setPresenter(this);
  }

  @Override
  public void go(HasWidgets container) {
    container.clear();
    container.add(registerView.asWidget());
  }

  @Override
  public void onRegisterButtonClicked() {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void onGoToLoginButtonClicked() {
    eventBus.fireEvent(new GoToLoginButtonClickedEvent());
  }
}
