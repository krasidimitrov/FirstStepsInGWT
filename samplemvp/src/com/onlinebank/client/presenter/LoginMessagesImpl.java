package com.onlinebank.client.presenter;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class LoginMessagesImpl implements LoginMessages{
  @Override
  public String usernameNotRegistered() {
    return "This username is not registered";
  }

  @Override
  public String wrongPassword() {
    return "Password doesn't match";
  }
}
