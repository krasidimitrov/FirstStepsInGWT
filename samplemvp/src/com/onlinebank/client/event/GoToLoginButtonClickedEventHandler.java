package com.onlinebank.client.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public interface GoToLoginButtonClickedEventHandler extends EventHandler{
  void onGoToLoginButtonClicked(GoToLoginButtonClickedEvent event);
}
