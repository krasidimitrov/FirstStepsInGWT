package com.clouway.sampleRF.client;

import com.google.gwt.user.client.ui.IsWidget;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public interface SimpleView extends IsWidget{
  
  String getNameValue();
  String getNickValue();
  String getPhoneValue();
  String getOccupationValue();

  void setNameValue(String name);
  void setNickValue(String nick);
  void setPhoneValue(String phone);
  void setOccupationValue(String occupation);


  SimpleViewImpl.Driver getDriver();


  void showSuccessMessage();

  public interface Presenter{
    void onSaveButtonClicked();
    void onSelectButtonClicked();
    void onEditButtonClicked();
  }

  void setPresenter(Presenter presenter);
}
