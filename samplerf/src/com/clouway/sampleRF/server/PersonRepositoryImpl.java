package com.clouway.sampleRF.server;

import com.google.inject.Inject;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class PersonRepositoryImpl implements PersonRepository{

  @Inject
  DatabaseHelper databaseHelper;

//  public PersonRepositoryImpl(DatabaseHelper databaseHelper){
//    this.databaseHelper = databaseHelper;
//  }

//  public PersonRepositoryImpl(){
//    databaseHelper = new DatabaseHelper();
//  }

  @Override
  public void save(Person person) {
    databaseHelper.executeQuery("INSERT INTO Persons(username, nickname, phone, occupation) VALUES (?,?,?,?);", person.getRealName(), person.getNickName(), person.getPhone(), person.getOccupation());
  }

  @Override
  public Person getPersonFromNick(String nick) {
    return databaseHelper.executeQueryThatReturnSpecificObject("SELECT * FROM Persons WHERE nickname = ?;",new PersonRowMapper(), nick);
  }

  @Override
  public void update(Person person) {
    databaseHelper.executeQuery("UPDATE Persons SET username = ?, phone = ?, occupation = ? WHERE nickname = ?;", person.getRealName(), person.getPhone(), person.getOccupation(), person.getNickName());
  }
}
