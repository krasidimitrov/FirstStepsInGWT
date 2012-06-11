package com.onlinebank.client.model;

import java.math.BigDecimal;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class Account {
  
  int userId;
  int accountId;
  BigDecimal balance;

  public Account(int userId, int accountId, BigDecimal balance){
    this.userId = userId;
    this.accountId = accountId;
    this.balance = balance;
  }
}
