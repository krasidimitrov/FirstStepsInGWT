package com.onlinebank.client.view;

import com.google.gwt.user.client.ui.Widget;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public interface LoginView {


  String getUsername();
  String getPassword();

  void goToUserPage();

  void setStatusMessage(String message);

  public interface Presenter {
    void onLoginButtonClicked();
    void onGotToRegistrationButtonClicked();
  }

  void setPresenter(Presenter presenter);
  Widget asWidget();
}
