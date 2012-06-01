package com.onlinebank.client.presenter;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class RegistrationMessageImpl implements RegistrationMessages{
  @Override
  public String registrationSuccessful() {
    return "Registration successful!";
  }

  @Override
  public String WrongUsernameOrPasswordFormat() {
    return "Username and password must be between 5 and 20 symbols. Letters and numbers only";
  }

  @Override
  public String usernameAlreadyExists() {
    return "Username already exists. Try another one.";
  }
}
