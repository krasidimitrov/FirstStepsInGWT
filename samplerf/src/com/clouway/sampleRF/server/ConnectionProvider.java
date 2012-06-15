package com.clouway.sampleRF.server;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class ConnectionProvider implements com.google.inject.Provider<Connection> {


  @Override
  public Connection get() {
    try {
      MysqlConnectionPoolDataSource dataSource = new MysqlConnectionPoolDataSource();
      dataSource.setServerName("localhost");
      dataSource.setDatabaseName("GWTBankDatabase");
      dataSource.setUser("kpackapgo");
      dataSource.setPassword("");
      return dataSource.getConnection();
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }

//    return DatabaseConnectionFilter.connectionThreadLocal.get();
  }
}
