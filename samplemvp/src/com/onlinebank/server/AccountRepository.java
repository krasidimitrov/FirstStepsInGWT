package com.onlinebank.server;

import java.math.BigDecimal;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public interface AccountRepository {
  void addAccount(int userId);
  int getAccountId(int userId);
  BigDecimal getBalance(int accountId);
  void updateBalance(int accountId, BigDecimal balance);
}
