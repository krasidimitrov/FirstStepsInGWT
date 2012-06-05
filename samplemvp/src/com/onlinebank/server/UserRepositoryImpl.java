package com.onlinebank.server;

import com.onlinebank.client.model.User;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class UserRepositoryImpl implements UserRepository {

  DatabaseHelper databaseHelper;

  public UserRepositoryImpl(DatabaseHelper databaseHelper) {

    this.databaseHelper = databaseHelper;
  }

//  @Override
//  public String getUsername(String username) {
//    return databaseHelper.executeQueryWithResult("SELECT username FROM Users WHERE username = ?;", username);
//  }
//
//  @Override
//  public int getUserId(String username) {
//    return Integer.parseInt(databaseHelper.executeQueryWithResult("SELECT username FROM Users WHERE username = ?;",username));
//  }

  @Override
  public int save(User user) {
    return databaseHelper.executeQueryThatReturnAutoIncrementedValue("INSERT INTO Users(username,password) VALUES(?,?);", user.getUsername(), user.getPassword());
  }

  @Override
  public User getUser(String username) {
    return databaseHelper.executeQueryThatReturnSpecificObject("SELECT * FROM Users WHERE username = ?", new UserRowMapper(), username);
  }


//  @Override
//  public String getPassword(String username) {
//    return databaseHelper.executeQueryWithResult("SELECT password FROM Users WHERE username = ?;", username);
//  }
}
