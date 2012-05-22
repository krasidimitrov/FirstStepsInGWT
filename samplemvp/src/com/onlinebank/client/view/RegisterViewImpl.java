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

  public RegisterViewImpl() {
    initWidget(ourUiBinder.createAndBindUi(this));
  }

  public void setPresenter(Presenter presenter){
    this.presenter = presenter;
  }

  @UiHandler("goToLoginButton")
  void onGoToLoginButtonClicked(ClickEvent event){
    if(presenter != null){
      presenter.onGoToLoginButtonClicked();
    }
  }
}