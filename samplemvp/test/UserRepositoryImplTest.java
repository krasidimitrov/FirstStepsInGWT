import com.onlinebank.client.model.User;
import com.onlinebank.server.DatabaseHelper;
import com.onlinebank.server.UserRepositoryImpl;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;


/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class UserRepositoryImplTest {

  DatabaseHelper databaseHelper = new DatabaseHelper("GWTBankDatabase");

  UserRepositoryImpl userRepository = new UserRepositoryImpl(databaseHelper);

  @Before
  public void cleanUp(){
    databaseHelper.executeQuery("DELETE FROM Users;");
  }

//  @Test
//  public void returnsPasswordForExistingUser() {
//    userRepository.save(new User("Lilith", "abc123"));
//    String password = userRepository.getPassword("Lilith");
//    assertThat(password, is(equalTo("abc123")));
//  }
//
//  @Test
//  public void emptyPasswordIsReturnedForNonExistingUser() {
//    String password = userRepository.getPassword("Lilith");
//    assertThat(password, is(equalTo("")));
//  }
//
//  @Test
//  public void returnEmptyStringIfUserIsNotRegistered(){
//    String username = userRepository.getUsername("Lilith");
//    assertThat(username, is(equalTo("")));
//  }
//
//  @Test
//  public void returnUsernameFoo(){
//    userRepository.save(new User("Lilith", "abc123"));
//    String username = userRepository.getUsername("Lilith");
//    assertThat(username, is(equalTo("Lilith")));
//  }

  @Test
  public void returnIdOfANewRegisteredUser(){
    int id = userRepository.save(new User("Lilith", "abs123"));
    assertThat(id, is(not(-1)));
  }
  
  @Test
  public void returnUserWithEmptyUsernameForNonExistingUser(){
    User user = userRepository.getUser("Lilith");  
    assertThat(user, is(equalTo(null)));
  }

}
