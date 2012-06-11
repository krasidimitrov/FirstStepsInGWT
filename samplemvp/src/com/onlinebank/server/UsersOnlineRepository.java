package com.onlinebank.server;

import com.onlinebank.client.model.Sid;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public interface UsersOnlineRepository {
  
  void save(Sid sid, int timeLimitInSeconds);

  void updateExpirationTime(Sid sid, int timeLimitInSeconds);
  
  String getSid(String token);

  String getUsername(String currentSid);
}
