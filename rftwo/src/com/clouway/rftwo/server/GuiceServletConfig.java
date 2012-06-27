package com.clouway.rftwo.server;


import com.clouway.rftwo.inject.MyConstraintValidatorFactory;
import com.clouway.rftwo.inject.MyRequestFactoryServlet;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
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
        serve("/gwtRequest").with(MyRequestFactoryServlet.class);

        bind(Connection.class).toProvider(ConnectionProvider.class);
        bind(DatabaseHelper.class).asEagerSingleton();
        bind(ProductRepository.class).to(ProductRepositoryImpl.class);
//        bind(new TypeLiteral<Provider<Connection>>(){}).to(ConnectionProvider.class).in(Singleton.class);
      }

      @Provides
      @Singleton
      ValidatorFactory getValidatorFactory(Injector injector) {
        return Validation.byDefaultProvider().configure().constraintValidatorFactory(new MyConstraintValidatorFactory(injector)).buildValidatorFactory();
      }

      @Provides @Singleton
      Validator getValidator(ValidatorFactory validatorFactory) {
        return validatorFactory.getValidator();
      }
    });
  }
}
