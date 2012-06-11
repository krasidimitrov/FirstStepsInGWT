package com.onlinebank.server;


import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.matcher.Matchers;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

import java.sql.Connection;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class GuiceServletConfig extends GuiceServletContextListener {

  Provider<Connection> connectionProvider;
  @Override
  protected Injector getInjector() {
    return Guice.createInjector(new ServletModule(){
      @Override
      protected void configureServlets() {
//      connectionProvider = new ConnectionProvider();
      TransactionInterceptor transactionInterceptor = new TransactionInterceptor();
        serve("/MySampleApplication/RegisterService").with(RegisterServiceImpl.class);
        serve("/MySampleApplication/LoginService").with(LoginServiceImpl.class);
        serve("/MySampleApplication/OnlineBankService").with(BankServiceImpl.class);
        serve("/Bank.html").with(MainPageServlet.class);

        filter("/*").through(DatabaseConnectionFilter.class);

        requestInjection(transactionInterceptor);
        bindInterceptor(Matchers.any(), Matchers.annotatedWith(InTransaction.class), transactionInterceptor);
        bind(UserRepository.class).to(UserRepositoryImpl.class);
        bind(AccountRepository.class).to(AccountRepositoryImpl.class);
        bind(UsersOnlineRepository.class).to(UsersOnlineRepositoryImpl.class);
        bind(DatabaseHelper.class).asEagerSingleton();
        bind(new TypeLiteral<Provider<Connection>>(){}).to(ConnectionProvider.class).in(Singleton.class);
      }

//      @Provides
//      @Singleton Provider<Connection> provideConnection() {
//        return connectionProvider;
//      }
      
//      @Provides
//      DatabaseHelper provideDatabaseHelper(){
//        return new DatabaseHelper(new ConnectionProvider());
//      }
    });
  }
}
