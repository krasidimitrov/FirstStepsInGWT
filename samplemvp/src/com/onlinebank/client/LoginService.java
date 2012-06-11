package com.onlinebank.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.server.rpc.XsrfProtect;
import com.onlinebank.client.exception.UserNotRegisteredException;
import com.onlinebank.client.model.Sid;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */

@RemoteServiceRelativePath("LoginService")
public interface LoginService extends RemoteService {

  @XsrfProtect
  Sid login(String username, String password) throws UserNotRegisteredException;

}
