package com.onlinebank.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class UserPanelView extends Composite{
  interface UserPanelViewUiBinder extends UiBinder<HTMLPanel, UserPanelView> {
  }

  private static UserPanelViewUiBinder ourUiBinder = GWT.create(UserPanelViewUiBinder.class);

  public UserPanelView() {
    initWidget(ourUiBinder.createAndBindUi(this));

  }
}