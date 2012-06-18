package com.clouway.sampleRF.client;

import com.clouway.sampleRF.server.PersonRepository;
import com.clouway.sampleRF.server.PersonRepositoryLocator;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.RequestFactory;
import com.google.web.bindery.requestfactory.shared.Service;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public interface PersonRequestFactory extends RequestFactory{

  @Service(value = PersonRepository.class, locator = PersonRepositoryLocator.class)
  interface PersonRequest extends RequestContext {

//    Request<Void> save(PersonProxy personProxy);
    Request<Void> save(String name, String nick);
  }

  PersonRequest personRequest();
}
