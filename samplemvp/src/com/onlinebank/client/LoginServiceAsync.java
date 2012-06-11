package com.onlinebank.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.onlinebank.client.model.Sid;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */

public interface LoginServiceAsync {
  void login(String username, String password, AsyncCallback<Sid> async);
}
