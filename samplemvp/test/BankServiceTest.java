import com.onlinebank.client.exception.IncorrectDataFormatException;
import com.onlinebank.client.exception.InsufficientBalanceException;
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

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
@RunWith(JMock.class)
public class BankServiceTest {

  Mockery context = new JUnit4Mockery();

  private User user = new User("Lilith", "abc123");
  private int accontId = 1;
  private UserRepository userRepository = context.mock(UserRepository.class);
  private AccountRepository accountRepository = context.mock(AccountRepository.class);

  private BankServiceImpl bankService = new BankServiceImpl(userRepository);

  class BankServiceImpl {
    UserRepository userRepository;
    int accId = 1;


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

    public BigDecimal deposit(BigDecimal ammount) {
      BigDecimal currentBalance = accountRepository.getBalance(accId);
      BigDecimal newBalance = currentBalance.add(ammount);
      accountRepository.updateBalance(accId,newBalance);

      return newBalance; 
    }

    public BigDecimal withdraw(BigDecimal ammount) {
      
      BigDecimal currentBalance = accountRepository.getBalance(accId);
      BigDecimal newBalance = currentBalance.subtract(ammount);
      if(newBalance.compareTo(BigDecimal.ZERO) == -1){
        throw new InsufficientBalanceException();
      } else {
        accountRepository.updateBalance(accId, newBalance);
        return newBalance;
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

    BigDecimal getBalance(int accontId);

    void updateBalance(int accontId, BigDecimal newBalance);
  }

  class AccountRepositoryImpl implements AccountRepository {

    @Override
    public void createAccount(int userId) {
      //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public BigDecimal getBalance(int accontId) {
      return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void updateBalance(int accontId, BigDecimal newBalance) {
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

  @Test
  public void depositHappyPath(){
    final BigDecimal currentBalance = new BigDecimal(100);
    final BigDecimal newBalance = new BigDecimal(150);
    final BigDecimal actualNewBalance;
     context.checking(new Expectations(){{
       oneOf(accountRepository).getBalance(accontId);
       will(returnValue(currentBalance));
       oneOf(accountRepository).updateBalance(accontId, newBalance);
     }});
    
    actualNewBalance = bankService.deposit(new BigDecimal(50));
    assertEquals(newBalance, actualNewBalance);
  }
  
  
  
  @Test
  public void withdrawHappyPath(){
    final BigDecimal currentBalance = new BigDecimal(100);
    final BigDecimal newBalance = new BigDecimal(50);
    final BigDecimal actualBalance;
    
    context.checking(new Expectations(){{
      oneOf(accountRepository).getBalance(accontId);
      will(returnValue(currentBalance));
      oneOf(accountRepository).updateBalance(accontId, newBalance);
    }});
    
    actualBalance = bankService.withdraw(new BigDecimal(50));
    assertEquals(newBalance, actualBalance);
  }

  
  @Test (expected = InsufficientBalanceException.class)
  public void withdrawWillThrowExceptionIfTheCurrentBalanceIsNotEnough(){
    final BigDecimal currentBalance = new BigDecimal(100);
    final BigDecimal newBalance = new BigDecimal(-100);
    
    context.checking(new Expectations(){{
      oneOf(accountRepository).getBalance(accontId);
      will(returnValue(currentBalance));
    }
    });
    
    bankService.withdraw(new BigDecimal(200));
  }
}
