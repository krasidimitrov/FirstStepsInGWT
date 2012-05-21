package com.onlinebank.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.onlinebank.client.presenter.Presenter;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class UserPanelView extends Composite{

  private Presenter presenter;

  public void setPresenter(Presenter presenter){
    this.presenter = presenter;
  }


  interface UserPanelViewUiBinder extends UiBinder<HTMLPanel, UserPanelView> {
  }

  private static UserPanelViewUiBinder ourUiBinder = GWT.create(UserPanelViewUiBinder.class);

  public UserPanelView() {
    initWidget(ourUiBinder.createAndBindUi(this));

  }
}