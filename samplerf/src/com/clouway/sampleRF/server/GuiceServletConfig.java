package com.clouway.sampleRF.server;


import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

import java.sql.Connection;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class GuiceServletConfig extends GuiceServletContextListener {

  @Override
  protected Injector getInjector() {
    return Guice.createInjector(new ServletModule(){
      @Override
      protected void configureServlets() {

//        serve("/MySampleApplication/RegisterService").with(RegisterServiceImpl.class);
//        serve("/MySampleApplication/LoginService").with(LoginServiceImpl.class);
//        serve("/MySampleApplication/OnlineBankService").with(BankServiceImpl.class);

//        filter("/*").through(DatabaseConnectionFilter.class);

        bind(Connection.class).toProvider(ConnectionProvider.class);
        bind(DatabaseHelper.class).asEagerSingleton();
//        bind(new TypeLiteral<Provider<Connection>>(){}).to(ConnectionProvider.class).in(Singleton.class);
      }

//      @Provides
//      @Singleton Providerdsa<Connection> provideConnection() {
//        return connectionProvider;
//      }

//      @Provides
//      DatabaseHelper provideDatabaseHelper(){
//        return new DatabaseHelper(new ConnectionProvider());
//      }
    });
  }
}
