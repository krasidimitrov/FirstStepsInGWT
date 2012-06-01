import com.onlinebank.client.exception.IncorrectDataFormatException;
import com.onlinebank.client.exception.UserNotRegisteredException;
import com.onlinebank.client.exception.UsernameAlreadyExistsException;
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
public class BankServiceTest {

  Mockery context = new JUnit4Mockery();

  User user = new User("Lilith", "abc123");
  UserRepository userRepository = context.mock(UserRepository.class);
  AccountRepository accountRepository = context.mock(AccountRepository.class);

  BankServiceImpl bankService = new BankServiceImpl(userRepository);

  class BankServiceImpl {
    UserRepository userRepository;

    public BankServiceImpl(UserRepository userRepository) {
      this.userRepository = userRepository;
    }

    void register(User user) {
      int userId;
      if (user.getUsername().matches("^[A-Za-z0-9]{5,20}$") && user.getPassword().matches("^[A-Za-z0-9]{5,20}$")) {
        if (userRepository.getUsername(user.getUsername()).equals("")) {
          userId = userRepository.getUserId(user.getUsername());
          userRepository.save(user);
          accountRepository.createAccount(userId);
        } else {
          throw new UsernameAlreadyExistsException();
        }
      } else {
        throw new IncorrectDataFormatException();
      }
    }

    public void login(String username, String password) {
      String passwordInRepository = userRepository.getPassword(username);
      if(passwordInRepository.equals("")){
        throw new UserNotRegisteredException();
      } else if(!passwordInRepository.equals(password))  {
        throw new WrongPasswordException();
      }

    }
  }

  interface UserRepository {
    String getUsername(String username);

    int getUserId(String username);

    void save(User user);

    String getPassword(String username);
  }

  class UserRepositoryImpl implements UserRepository {

    @Override
    public String getUsername(String username) {
      return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getUserId(String username) {
      return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void save(User user) {
      //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getPassword(String username) {
      return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
  }

  interface AccountRepository {
    void createAccount(int userId);

  }

  class AccountRepositoryImpl implements AccountRepository {

    @Override
    public void createAccount(int userId) {
      //To change body of implemented methods use File | Settings | File Templates.
    }
  }

  @Test
  public void registerWillSendTheUserForSavingAlongWithAccount() {
    final int userId = 1;
    context.checking(new Expectations() {{
      oneOf(userRepository).getUsername(user.getUsername());
      will(returnValue(""));
      oneOf(userRepository).save(user);
      oneOf(userRepository).getUserId(user.getUsername());
      will(returnValue(userId));
      oneOf(accountRepository).createAccount(userId);
    }});

    bankService.register(user);
  }

  @Test(expected = UsernameAlreadyExistsException.class)
  public void registerWillThrowExceptionIfUsernameAlreadyExists() {

    context.checking(new Expectations() {
      {

        oneOf(userRepository).getUsername(user.getUsername());
        will(returnValue("Lilith"));
      }
    });

    bankService.register(user);

  }
  
  @Test(expected = IncorrectDataFormatException.class)
  public void registerWillThrowExceptionIfUsernameIsIncorrectFormat(){
    final User userWithIncorrectUsername = new User("Lilith%" , "correct");
    bankService.register(userWithIncorrectUsername);
  }
  @Test(expected = IncorrectDataFormatException.class)
  public void registerWillThrowExceptionIfPasswordIsIncorrectFormat(){
    final User userWithIncorrectPassword = new User("Lilith" , "whe!r$");
    bankService.register(userWithIncorrectPassword);
  }

  @Test
  public void loginHappyPath(){
    context.checking(new Expectations(){{
      oneOf(userRepository).getPassword(user.getUsername());
      will(returnValue("abc123"));
    }});
    
    bankService.login(user.getUsername(),user.getPassword());
  }
  
  @Test (expected = UserNotRegisteredException.class)
  public void loginWillThrowExceptionIfUsernameIsNotRegistered(){
    
    context.checking(new Expectations(){{
      oneOf(userRepository).getPassword(user.getUsername());
      will(returnValue(""));

    }});

    bankService.login(user.getUsername(), user.getPassword());
  }
  
  @Test(expected = WrongPasswordException.class)
  public void loginWillThrowExceptionIfPasswordIsIncorrect(){
  
    context.checking(new Expectations(){{
      oneOf(userRepository).getPassword(user.getUsername());
      will(returnValue("notMatching"));
    }});
    
    bankService.login(user.getUsername(), user.getPassword());
  }

}
