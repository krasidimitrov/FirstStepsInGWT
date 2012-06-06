package com.onlinebank.server;

import com.onlinebank.client.exception.IncorrectDataFormatException;
import com.onlinebank.client.model.User;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */

@RunWith(JMock.class)
public class RegisterServiceImplTest {
  
  Mockery context = new JUnit4Mockery();
  UserRepository userRepository = context.mock(UserRepository.class);
  AccountRepository accountRepository = context.mock(AccountRepository.class);
  User user = new User("Lilith","abc123");
  RegisterServiceImpl registerService = new RegisterServiceImpl(userRepository,accountRepository);


  @Test
  public void registerWillSendTheUserForSavingAlongWithAccount() {
    final int userId = 1;
    context.checking(new Expectations() {{
      oneOf(userRepository).getUser(user.getUsername());
      will(returnValue(null));
      oneOf(userRepository).save(user);
      will(returnValue(userId));
      oneOf(accountRepository).addAccount(userId);
    }});

    registerService.register(user);
  }


  @Test(expected = IncorrectDataFormatException.class)
  public void registerWillThrowExceptionIfUsernameIsIncorrectFormat() {
    final User userWithIncorrectUsername = new User("Lilith%", "correct");
    registerService.register(userWithIncorrectUsername);
  }

  @Test(expected = IncorrectDataFormatException.class)
  public void registerWillThrowExceptionIfPasswordIsIncorrectFormat() {
    final User userWithIncorrectPassword = new User("Lilith", "whe!r$");
    registerService.register(userWithIncorrectPassword);
  }

}
