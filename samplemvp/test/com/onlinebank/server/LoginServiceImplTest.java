package com.onlinebank.server;

import com.onlinebank.client.exception.UserNotRegisteredException;
import com.onlinebank.client.exception.WrongPasswordException;
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
public class LoginServiceImplTest {

  Mockery context = new JUnit4Mockery();
  private UserRepository userRepository = context.mock(UserRepository.class);
  private AccountRepository accountRepository = context.mock(AccountRepository.class);
  private User user = new User("Lilith","abc123");
  
  private LoginServiceImpl loginService = new LoginServiceImpl(userRepository, accountRepository);

  @Test
  public void loginHappyPath() {
    context.checking(new Expectations() {{
      oneOf(userRepository).getUser("Lilith");
      will(returnValue(user));
    }});

    loginService.login("Lilith", "abc123");
  }

  @Test(expected = UserNotRegisteredException.class)
  public void loginWillThrowExceptionIfUsernameIsNotRegistered() {

    context.checking(new Expectations() {{
      oneOf(userRepository).getUser("Lilith");
      will(returnValue(null));

    }});

    loginService.login("Lilith", "abc123");
  }

  @Test(expected = WrongPasswordException.class)
  public void loginWillThrowExceptionIfPasswordIsIncorrect() {

    context.checking(new Expectations() {{
      oneOf(userRepository).getUser("Lilith");
      will(returnValue(user));
    }});

    loginService.login("Lilith", "wrongPassword");
  }
}
