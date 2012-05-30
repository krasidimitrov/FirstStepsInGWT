package com.onlinebank.client.presenter;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class RegistrationMessageImpl implements RegistrationMessages{
  @Override
  public String getSuccessMessage() {
    return "Registration successful!";
  }

  @Override
  public String getWrongUsernameOrPasswordMessage() {
    return "Username and password must be between 5 and 20 symbols. Letters and numbers only";
  }

  @Override
  public String getUsernameAlreadyExistsMessage() {
    return "Username already exists. Try another one.";
  }
}
