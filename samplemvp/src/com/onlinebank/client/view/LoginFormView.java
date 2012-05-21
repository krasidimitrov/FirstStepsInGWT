package com.onlinebank.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.onlinebank.client.MySampleApplication;
import com.onlinebank.client.presenter.Presenter;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class LoginFormView extends Composite implements View {

  private Presenter presenter;

  public void setPresenter(Presenter presenter){
    this.presenter = presenter;
  }


  interface LoginFormViewUiBinder extends UiBinder<HTMLPanel, LoginFormView> {
  }

  private static LoginFormViewUiBinder ourUiBinder = GWT.create(LoginFormViewUiBinder.class);

  @UiField
  Button goToRegistrationButton;

  public LoginFormView() {
    initWidget(ourUiBinder.createAndBindUi(this));

    goToRegistrationButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
//        RegisterFormView registerFormView = new RegisterFormView();
//        RootPanel.get().remove(0);
//        RootPanel.get().add(registerFormView);
        MySampleApplication.setView(new RegisterFormView());
      }
    });
  }
}