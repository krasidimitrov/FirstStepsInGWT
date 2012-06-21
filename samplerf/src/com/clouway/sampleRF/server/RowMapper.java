package com.clouway.sampleRF.server;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public interface RowMapper<T> {
  
  T map(ResultSet resultSet) throws SQLException;
}
