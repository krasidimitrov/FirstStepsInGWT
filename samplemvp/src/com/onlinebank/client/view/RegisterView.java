package com.onlinebank.client.view;

import com.onlinebank.client.model.User;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public interface RegisterView  {

  User getUser();
  public void setStatusMessage(String text);


  void redirectToMainPage();

  public interface Presenter {
    void onRegisterButtonClicked();
    void onGoToLoginButtonClicked();
  }

  void setPresenter(Presenter presenter);
}
