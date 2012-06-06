package com.onlinebank.server;


import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class GuiceServletConfig extends GuiceServletContextListener {
  @Override
  protected Injector getInjector() {
    return Guice.createInjector(new ServletModule(){
      @Override
      protected void configureServlets() {

        serve("/MySampleApplication/RegisterService").with(RegisterServiceImpl.class);
        serve("/MySampleApplication/LoginService").with(LoginServiceImpl.class);
        serve("/MySampleApplication/OnlineBankService").with(BankServiceImpl.class);



        bind(UserRepository.class).to(UserRepositoryImpl.class);
        bind(AccountRepository.class).to(AccountRepositoryImpl.class);

      }
    });
  }
}
