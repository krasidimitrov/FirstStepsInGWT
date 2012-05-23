package com.onlinebank.client.view;

import com.google.gwt.user.client.ui.Widget;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public interface LoginView {

  public interface Presenter {
    void onLoginButtonClicked();
    void onGotToRegistrationButtonClicked();
  }

  void setPresenter(Presenter presenter);
  Widget asWidget();
}
