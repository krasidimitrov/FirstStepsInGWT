package com.onlinebank.server;

import com.onlinebank.client.model.User;
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

  DatabaseHelper databaseHelper = new DatabaseHelper();

  UserRepositoryImpl userRepository = new UserRepositoryImpl(databaseHelper);

  @Before
  public void cleanUp(){
    databaseHelper.executeQuery("DELETE FROM Users;");
  }


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
