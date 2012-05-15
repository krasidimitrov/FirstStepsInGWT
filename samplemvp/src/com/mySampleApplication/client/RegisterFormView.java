package com.mySampleApplication.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class RegisterFormView extends Composite {
  interface RegisterFormViewUiBinder extends UiBinder<HTMLPanel, RegisterFormView> {
  }

  private static RegisterFormViewUiBinder ourUiBinder = GWT.create(RegisterFormViewUiBinder.class);

  public RegisterFormView() {
    initWidget(ourUiBinder.createAndBindUi(this));
  }
}