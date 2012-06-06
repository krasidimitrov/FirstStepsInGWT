package com.onlinebank.server;

import com.google.inject.Inject;
import com.onlinebank.client.model.User;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class UserRepositoryImpl implements UserRepository {

  DatabaseHelper databaseHelper;

  @Inject
  public UserRepositoryImpl(DatabaseHelper databaseHelper) {

    this.databaseHelper = databaseHelper;
  }

  @Override
  public int save(User user) {
    return databaseHelper.executeQueryThatReturnAutoIncrementedValue("INSERT INTO Users(username,password) VALUES(?,?);", user.getUsername(), user.getPassword());
  }

  @Override
  public User getUser(String username) {
    return databaseHelper.executeQueryThatReturnSpecificObject("SELECT * FROM Users WHERE username = ?", new UserRowMapper(), username);
  }

}
