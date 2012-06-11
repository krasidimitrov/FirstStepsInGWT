package com.onlinebank.client.model;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class Sid implements IsSerializable{
  private String sid;
  private String username;
  private int accountId;


  public Sid(){

  }

  public Sid(String sid, String username, int accountId){
    this.sid = sid;
    this.username = username;
    this.accountId = accountId;
  }

  public String getSid() {
    return sid;
  }

  public String getUsername() {
    return username;
  }

  public int getAccountId() {
    return accountId;
  }
}
