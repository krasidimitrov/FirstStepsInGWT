package com.onlinebank.server;

import com.onlinebank.client.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class UserRowMapper implements RowMapper<User>{

  @Override
  public User map(ResultSet resultSet) throws SQLException {

    int userId = resultSet.getInt("userId");
    String username = resultSet.getString("username");
    String password = resultSet.getString("password");

    return new User(userId, username, password);
  }
}
