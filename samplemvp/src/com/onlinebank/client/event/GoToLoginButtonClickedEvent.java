package com.onlinebank.client.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class GoToLoginButtonClickedEvent extends GwtEvent<GoToLoginButtonClickedEventHandler>{

  public static Type<GoToLoginButtonClickedEventHandler> TYPE = new Type<GoToLoginButtonClickedEventHandler>();

  @Override
  public Type<GoToLoginButtonClickedEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(GoToLoginButtonClickedEventHandler handler) {
    handler.onGoToLoginButtonClicked(this);
  }
}
