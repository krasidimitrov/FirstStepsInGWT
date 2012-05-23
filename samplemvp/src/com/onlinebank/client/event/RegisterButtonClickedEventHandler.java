package com.onlinebank.client.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public interface RegisterButtonClickedEventHandler extends EventHandler{
  void onRegisterButtonClicked(RegisterButtonClickedEvent event);
}
