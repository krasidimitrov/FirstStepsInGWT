package com.onlinebank.client.model;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class User implements IsSerializable {

  private int userId;
  private String username;
  private String password;

  public User(){

  }

  public User(String username, String password){
    userId=0;
    this.username = username;
    this.password = password;
  }

  public User(int userId, String username, String password) {
    this.userId = userId;
    this.username = username;
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public int getUserId() {
    return userId;
  }

  public boolean isValid() {
    if (!username.matches("^[A-Za-z0-9]{5,20}$") || !password.matches("^[A-Za-z0-9]{5,20}$")) {
      return false;
    }
    return true;
  }
}
