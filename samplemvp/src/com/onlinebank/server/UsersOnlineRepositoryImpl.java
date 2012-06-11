package com.onlinebank.server;

import com.google.inject.Inject;
import com.onlinebank.client.model.Sid;

import java.sql.Time;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class UsersOnlineRepositoryImpl implements UsersOnlineRepository{

  
  DatabaseHelper databaseHelper;
  
  @Inject
  public UsersOnlineRepositoryImpl(DatabaseHelper databaseHelper){

    this.databaseHelper = databaseHelper;
  }
  
  
  
  @Override
  public void save(Sid sid, int timeLimitInSeconds) {
    databaseHelper.executeQuery("INSERT INTO UsersOnline (token, username, creationTime, expirationTime) VALUES (?, ?, ?, ?);", sid.getSid(), sid.getUsername(), new Time(System.currentTimeMillis()), new Time(System.currentTimeMillis() + timeLimitInSeconds * 1000));
  }

  @Override
  public void updateExpirationTime(Sid sid, int timeLimitInSeconds) {
    databaseHelper.executeQuery("UPDATE UsersOnline SET expirationTime=? WHERE token=? AND username = ?;", new Time(System.currentTimeMillis()+ timeLimitInSeconds *1000), sid.getSid(), sid.getUsername());
  }

  @Override
  public String getSid(String token) {
    return databaseHelper.executeQueryWithResult("SELECT token FROM UsersOnline WHERE token = ? AND creationTime<=? AND expirationTime >=? ",token, new Time(System.currentTimeMillis()), new Time(System.currentTimeMillis()));
  }

  @Override
  public String getUsername(String currentSid) {
    return databaseHelper.executeQueryWithResult("SELECT username FROM UsersOnline WHERE token = ?;", currentSid);
  }
}
