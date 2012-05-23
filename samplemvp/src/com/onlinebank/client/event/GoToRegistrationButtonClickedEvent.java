package com.onlinebank.client.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class GoToRegistrationButtonClickedEvent extends GwtEvent<GoToRegistrationButtonClickedEventHandler>{

  public static Type<GoToRegistrationButtonClickedEventHandler> TYPE = new Type<GoToRegistrationButtonClickedEventHandler>();

  @Override
  public Type<GoToRegistrationButtonClickedEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(GoToRegistrationButtonClickedEventHandler handler) {
    handler.onGoToRegistrationButtonClicked(this);
  }
}
