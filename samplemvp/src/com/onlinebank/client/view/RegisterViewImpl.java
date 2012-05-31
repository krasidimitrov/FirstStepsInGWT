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
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.onlinebank.client.model.User;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class RegisterViewImpl extends Composite implements RegisterView {

  private Presenter presenter;


  @UiTemplate("RegisterView.ui.xml")
  interface RegisterFormViewUiBinder extends UiBinder<HTMLPanel, RegisterViewImpl> {
  }

  private static RegisterFormViewUiBinder ourUiBinder = GWT.create(RegisterFormViewUiBinder.class);

  @UiField
  Button goToLoginButton;
  @UiField
  Button registerButton;
  @UiField
  Label statusMessageLabel;
  @UiField
  TextBox usernameTextBox;
  @UiField
  PasswordTextBox passwordTextBox;

  public RegisterViewImpl() {
    initWidget(ourUiBinder.createAndBindUi(this));
  }

  public void setPresenter(Presenter presenter) {
    this.presenter = presenter;
  }

  public User getUser() {
    return new User(usernameTextBox.getText(), passwordTextBox.getText());
  }

  @UiHandler("goToLoginButton")
  void onGoToLoginButtonClicked(ClickEvent event) {
    if (presenter != null) {
      presenter.onGoToLoginButtonClicked();
    }
  }

  @UiHandler("registerButton")
  void onRegisterButtonClicked(ClickEvent event) {
    if (presenter != null) {
      presenter.onRegisterButtonClicked();
    }
  }

  @Override
  public void setStatusMessage(String text) {
    statusMessageLabel.setText(text);
  }

}