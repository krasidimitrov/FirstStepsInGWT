//import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//
//
///**
// * Class for connecting to a database and executing queries
// * Created by Krasimir Dimitrov
// * Email: krasimir.dimitrov@clouway.com
// * Date: 2/16/12
// * Time: 4:51 PM
// * To change this template use File | Settings | File Templates.
// */
//public class DatabaseHelper {
//
//  private MysqlDataSource dataSource;
//
//  public DatabaseHelper(){
//    dataSource = new MysqlDataSource();
//    dataSource.setServerName("localhost");
//    dataSource.setDatabaseName("sampdb");
//    dataSource.setUser("kpackapgo");
//    dataSource.setPassword("");
//  }
//
//  /**
//   * Execute a query with 0 to n parameters
//   *
//   * @param query  the query that is executed in a prepare statement
//   * @param params the parameters used in the query which is executed
//   */
//  public void executeQuery(String query, Object... params) {
//    Connection connection = dataSource.getConnection();
//    try {
//
//      PreparedStatement preparedStatement = connection.prepareStatement(query);
//
//      fillParams(preparedStatement, params);
//
//      preparedStatement.execute();
//
//    } catch (SQLException e) {
//      e.printStackTrace();
//    }
//  }
//
//  /**
//   * Load the parameters fro a query  which is in a prepare statement
//   *
//   * @param preparedStatement the preparedStatement with the query for which we are going to fill the parameters
//   * @param params the parameters that we use to fill the query in the preparedStatement
//   * @throws SQLException if an error with the database occurs
//   */
//  private void fillParams(PreparedStatement preparedStatement, Object[] params) throws SQLException {
//    for (int i = 0; i < params.length; i++) {
//      preparedStatement.setObject(i + 1, params[i]);
//    }
//  }
//
//  /**
//   * Execute a query with 0 to N parameters and return the result set in a List of a given type
//   *
//   * @param query  the query that is executed
//   * @param params the parameters for the query that we are going to execute
//   * @return a List with objects from the type specific
//   */
//  public String executeQueryWithResult(String query, Object... params) {
//    Connection connection = dataSource.getConnection();
//    ResultSet resultSet = null;
//    String data = "";
//    try {
//      PreparedStatement preparedStatement = connection.prepareStatement(query);
//
//      fillParams(preparedStatement, params);
//      resultSet = preparedStatement.executeQuery();
//
//      if (resultSet.next()) {
//        data = resultSet.getString(1);
//      }
//    } catch (SQLException e) {
//      e.printStackTrace();
//    }
//    return data;
//  }
//
//}
