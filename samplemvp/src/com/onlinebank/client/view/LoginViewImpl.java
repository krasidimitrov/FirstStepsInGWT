package com.onlinebank.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class LoginViewImpl extends Composite implements LoginView {

  private Presenter presenter;

  public void setPresenter(Presenter presenter){
    this.presenter = presenter;
  }

  @UiTemplate("LoginView.ui.xml")
  interface LoginFormViewUiBinder extends UiBinder<HTMLPanel, LoginViewImpl> {
  }

  private static LoginFormViewUiBinder ourUiBinder = GWT.create(LoginFormViewUiBinder.class);

  @UiField
  Button goToRegistrationButton;

  public LoginViewImpl() {
    initWidget(ourUiBinder.createAndBindUi(this));
  }

  @UiHandler("goToRegistrationButton")
  void onGoToRegistrationButtonClicked(ClickEvent event ){
    if(presenter != null){
      presenter.onGotToRegistrationButtonClicked();
    }
  }


}