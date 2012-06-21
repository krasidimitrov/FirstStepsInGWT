package com.clouway.sampleRF.server;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class PersonRowMapper implements RowMapper<Person>{
  @Override
  public Person map(ResultSet resultSet) throws SQLException {
    Person person = new Person();
    person.setRealName(resultSet.getString("username"));
    person.setNickName(resultSet.getString("nickname"));
    person.setPhone(resultSet.getString("phone"));
    person.setOccupation(resultSet.getString("occupation"));
    return person;
  }
}
