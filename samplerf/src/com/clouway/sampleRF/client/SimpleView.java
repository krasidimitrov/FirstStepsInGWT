package com.clouway.sampleRF.client;

import com.google.gwt.user.client.ui.IsWidget;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public interface SimpleView extends IsWidget{
  
  String getNameValue();
  String getNickValue();
  void showSuccessMessage();
  void setPersonForEdit();

  public interface Presenter{
    void onSaveButtonClicked();
    void onSelectButtonClicked();
  }

  void setPresenter(Presenter presenter);
}
