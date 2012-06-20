package com.clouway.sampleRF.server;

import com.clouway.sampleRF.client.PersonProxy;

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
  public void save(PersonProxy personProxy) {
    databaseHelper.executeQuery("INSERT INTO Persons(username, nickname, phone, occupation) VALUES (?,?);", personProxy.getRealName(), personProxy.getNickName(), personProxy.getPhone(), personProxy.getOccupation());
  }
}
