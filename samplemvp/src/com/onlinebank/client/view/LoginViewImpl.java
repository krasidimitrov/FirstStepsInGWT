package com.onlinebank.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class LoginViewImpl extends Composite implements LoginView {

  private Presenter presenter;

  @UiTemplate("LoginView.ui.xml")
  interface LoginFormViewUiBinder extends UiBinder<HTMLPanel, LoginViewImpl> {
  }

  private static LoginFormViewUiBinder ourUiBinder = GWT.create(LoginFormViewUiBinder.class);

  @UiField
  Button goToRegistrationButton;
  @UiField
  Button loginButton;
  @UiField
  Label statusMessageLabel;
  @UiField
  TextBox usernameLoginTextBox;
  @UiField
  PasswordTextBox passwordLoginTextBox;

  public LoginViewImpl() {
    initWidget(ourUiBinder.createAndBindUi(this));
  }

  @UiHandler("goToRegistrationButton")
  void onGoToRegistrationButtonClicked(ClickEvent event ){
    if(presenter != null){
      presenter.onGotToRegistrationButtonClicked();
    }
  }

  @UiHandler("loginButton")
  void onLoginButtonClicked(ClickEvent event){
    if(presenter != null){
      presenter.onLoginButtonClicked();
    }
  }


  @Override
  public void goToUserPage() {
    History.newItem("userPanel");
  }

  @Override
  public void setStatusMessage(String message) {
    statusMessageLabel.setText(message);
  }

  public void setPresenter(Presenter presenter){
    this.presenter = presenter;
  }

  @Override
  public String getUsername() {
    return usernameLoginTextBox.getText();
  }

  @Override
  public String getPassword() {
    return passwordLoginTextBox.getText();
  }
}