package com.onlinebank.server;

import com.google.inject.Inject;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class TransactionInterceptor implements MethodInterceptor {

  @Inject
  Provider<Connection> connectionProvider;



  @Override
  public Object invoke(MethodInvocation methodInvocation) throws Throwable {

    Connection connection = connectionProvider.get();
    connection.setAutoCommit(false);
    Object obj = null;
    try {
      obj = methodInvocation.proceed();
      connection.commit();
    } catch (SQLException e){
      connection.rollback();
    }

    connection.setAutoCommit(true);


    return obj;
  }
}
