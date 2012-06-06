package com.onlinebank.server;

import com.onlinebank.client.exception.InsufficientBalanceException;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */

@RunWith(JMock.class)
public class BankServiceImplTest {


  private Mockery context = new JUnit4Mockery();
  private AccountRepository accountRepository = context.mock(AccountRepository.class);
  
  private BankServiceImpl bankService = new BankServiceImpl(accountRepository);
  private int accontId = 1;


  @Test
  public void depositHappyPath() {
    final BigDecimal currentBalance = new BigDecimal(100);
    final BigDecimal newBalance = new BigDecimal(150);
    final BigDecimal actualNewBalance;
    context.checking(new Expectations() {{
      oneOf(accountRepository).getBalance(accontId);
      will(returnValue(currentBalance));
      oneOf(accountRepository).updateBalance(accontId, newBalance);
    }});

    actualNewBalance = bankService.deposit(new BigDecimal(50));
    assertEquals(newBalance, actualNewBalance);
  }


  @Test
  public void withdrawHappyPath() {
    final BigDecimal currentBalance = new BigDecimal(100);
    final BigDecimal newBalance = new BigDecimal(50);
    final BigDecimal actualBalance;

    context.checking(new Expectations() {{
      oneOf(accountRepository).getBalance(accontId);
      will(returnValue(currentBalance));
      oneOf(accountRepository).updateBalance(accontId, newBalance);
    }});

    actualBalance = bankService.withdraw(new BigDecimal(50));
    assertEquals(newBalance, actualBalance);
  }


  @Test(expected = InsufficientBalanceException.class)
  public void withdrawWillThrowExceptionIfTheCurrentBalanceIsNotEnough() {
    final BigDecimal currentBalance = new BigDecimal(100);

    context.checking(new Expectations() {
      {
        oneOf(accountRepository).getBalance(accontId);
        will(returnValue(currentBalance));
      }
    });

    bankService.withdraw(new BigDecimal(200));
  }
}

