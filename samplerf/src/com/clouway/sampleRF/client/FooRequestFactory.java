package com.clouway.sampleRF.client;

import com.clouway.sampleRF.server.Person;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.RequestFactory;
import com.google.web.bindery.requestfactory.shared.Service;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public interface FooRequestFactory extends RequestFactory{

  @Service(Person.class)
  interface PersonRequest extends RequestContext {

//    Request<Void> save(PersonProxy personProxy);
    Request<Void> save(String name, String nick);
  }

  PersonRequest personRequest();
}
