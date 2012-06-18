package com.clouway.sampleRF.server;

import com.google.web.bindery.requestfactory.shared.ServiceLocator;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class PersonRepositoryLocator implements ServiceLocator {
  @Override
  public Object getInstance(Class<?> clazz) {
    return new PersonRepositoryImpl();
  }
}
