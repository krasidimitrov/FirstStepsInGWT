package com.onlinebank.server;

import com.onlinebank.client.model.Account;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class AccountRepositoryImplTest {

  private DatabaseHelper databaseHelper = new DatabaseHelper();

  private AccountRepositoryImpl accountRepository = new AccountRepositoryImpl(databaseHelper);
  
  private Account account;

  @Before
  public void cleanUp(){
    account = null;
    databaseHelper.executeQuery("DELETE FROM Accounts;");
  }

  @Test
  public void willCreateAccountWithEmptyBalance(){
    BigDecimal currentBalance;
    accountRepository.addAccount(1);
    int accountId = accountRepository.getAccountId(1);
    currentBalance =  accountRepository.getBalance(accountId);
    assertThat(currentBalance, is(equalTo(new BigDecimal("0.00"))));
  }
  
  @Test
  public void willChangeBalanceOfAccount(){
    BigDecimal currentBalance;
    accountRepository.addAccount(1);
    int accountId = accountRepository.getAccountId(1);
    accountRepository.updateBalance(accountId, new BigDecimal(555));
    currentBalance = accountRepository.getBalance(accountId);
    assertThat(currentBalance, is(equalTo(new BigDecimal("555.00"))));
  }
}
