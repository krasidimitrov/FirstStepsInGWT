package com.clouway.sampleRF.client;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public interface EditPersonView {
  
  void setPersonForEdit(String name);

  public interface Presenter{
    void onEditButtonClicked();
  }

  void setPresenter(Presenter presenter);
}
