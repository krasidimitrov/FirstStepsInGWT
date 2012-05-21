package com.onlinebank.client.view;

import com.google.gwt.user.client.ui.IsWidget;
import com.onlinebank.client.presenter.Presenter;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public interface View extends IsWidget{
  public void setPresenter(Presenter presenter);
}
