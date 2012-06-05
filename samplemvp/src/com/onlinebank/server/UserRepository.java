package com.onlinebank.server;

import com.onlinebank.client.model.User;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public interface UserRepository {


  int save(User user);

  User getUser(String username);
}
