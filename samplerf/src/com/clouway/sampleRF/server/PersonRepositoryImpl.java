package com.clouway.sampleRF.server;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class PersonRepositoryImpl implements PersonRepository{

  DatabaseHelper databaseHelper;

//  public PersonRepositoryImpl(DatabaseHelper databaseHelper){
//    this.databaseHelper = databaseHelper;
//  }

  public PersonRepositoryImpl(){
    databaseHelper = new DatabaseHelper();
  }

  @Override
  public void save(String name, String nick) {
    databaseHelper.executeQuery("INSERT INTO Persons(username, nickname) VALUES (?,?);", name, nick);
  }
}
