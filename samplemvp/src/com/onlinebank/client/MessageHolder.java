package com.onlinebank.client;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class MessageHolder {
  
  public Map<String, String> messages = new HashMap<String, String>();

  public MessageHolder(){
    messages.put("IncorrectRegisterData", "Username and password must be between 5 and 20 symbols letters and numbers only");
    messages.put("usernameAlreadyExists", "Username already exists.");
    messages.put("registrationSuccessful", "Registration Successful!");
    messages.put("IncorrectPassworld", "Password doesn't match the given username.");
    messages.put("IncorrectUsername", "Username doesn't exists!");
    messages.put("depositSuccessful", "Deposit Successful!");
    messages.put("withdrawSuccessful", "WIthdraw Successful!");
    messages.put("lowBalance", "You don't have enough money in the balance to make a withdraw.");
    messages.put("wrongMoneyFormat","Operation not completed! Use only numbers!");
  }
  
   public String getMessage(String key){
    return messages.get(key);
   }
}
