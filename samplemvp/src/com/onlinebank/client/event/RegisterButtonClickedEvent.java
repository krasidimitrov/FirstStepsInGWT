package com.onlinebank.client.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class RegisterButtonClickedEvent extends GwtEvent<RegisterButtonClickedEventHandler>{

  public static Type<RegisterButtonClickedEventHandler> TYPE = new Type<RegisterButtonClickedEventHandler>();

  @Override
  public Type<RegisterButtonClickedEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(RegisterButtonClickedEventHandler handler) {
    handler.onRegisterButtonClicked(this);
  }
}
