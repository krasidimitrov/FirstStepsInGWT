package com.onlinebank.server;


import com.google.inject.Inject;

import java.math.BigDecimal;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class AccountRepositoryImpl implements AccountRepository{
  private final DatabaseHelper databaseHelper;

  @Inject
  public AccountRepositoryImpl(DatabaseHelper databaseHelper) {
    this.databaseHelper = databaseHelper;
  }

  public void addAccount(int userId) {
    databaseHelper.executeQuery("INSERT INTO Accounts(userId, balance) VALUES (?, ?);", userId, 0);
  }

  public int getAccountId(int userId) {
    return Integer.parseInt(databaseHelper.executeQueryWithResult("SELECT accountId FROM Accounts WHERE userId = ?;", userId));
  }

  public BigDecimal getBalance(int accountId) {
    return new BigDecimal(databaseHelper.executeQueryWithResult("SELECT balance FROM Accounts WHERE accountId = ?;", accountId));
  }

  public void updateBalance(int accountId, BigDecimal balance) {
    databaseHelper.executeQuery("UPDATE Accounts SET balance = ? WHERE accountId = ?;", balance, accountId);
  }
}
