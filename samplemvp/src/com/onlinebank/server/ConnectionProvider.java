package com.onlinebank.server;

import java.sql.Connection;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class ConnectionProvider implements Provider<Connection>{

  @Override
  public Connection get() {
    return DatabaseConnectionFilter.connectionThreadLocal.get();
  }
}
