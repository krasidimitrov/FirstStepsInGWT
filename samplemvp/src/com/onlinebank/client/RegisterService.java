package com.onlinebank.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.server.rpc.NoXsrfProtect;
import com.onlinebank.client.exception.IncorrectDataFormatException;
import com.onlinebank.client.model.User;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */

@NoXsrfProtect
@RemoteServiceRelativePath("RegisterService")
public interface RegisterService extends RemoteService {
  void register(User user) throws IncorrectDataFormatException;
}