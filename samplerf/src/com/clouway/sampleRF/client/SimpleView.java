package com.clouway.sampleRF.client;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public interface SimpleView {
  
  String getNameValue();
  String getNickValue();
  void showSuccessMessage();

  public interface Presenter{
    void onSaveButtonClicked();
    void onGetButtonClicked();
  }

  void setPresenter(Presenter presenter);
}
