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
}
