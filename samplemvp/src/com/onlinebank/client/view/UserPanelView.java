package com.onlinebank.client.view;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public interface UserPanelView {


  void setBalanceShown(String text);

  void setStatusMessage(String text);

  String getWithdrawTextBoxText();

  String getDepositTextBoxText();
  

  public interface Presenter{
    void onDepositButtonClicked();
    void onWithdrawButtonClicked();
    void onLogoutButtonClicked();
  }

  void setPresenter(Presenter presenter);
}
