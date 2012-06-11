package com.onlinebank.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.onlinebank.client.model.User;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public interface RegisterServiceAsync {
  void register(User user, AsyncCallback<Void> async);
}
