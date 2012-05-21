package com.onlinebank.client.view;

import com.google.gwt.user.client.ui.Widget;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public interface RegisterView  {

  public interface Presenter {
    void onRegisterButtonClicked();
    void onReturnToLoginButtonClicked();
  }

  void setPresenter(Presenter presenter);
  Widget asWidget();
}
