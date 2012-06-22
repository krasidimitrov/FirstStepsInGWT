package com.clouway.sampleRF.client;

import com.clouway.sampleRF.server.PersonService;
import com.clouway.sampleRF.server.injecting.MyServiceLocator;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.RequestFactory;
import com.google.web.bindery.requestfactory.shared.Service;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public interface PersonRequestFactory extends RequestFactory{


  //Can it be a class on the value property instead of interface?
  @Service(value = PersonService.class, locator = MyServiceLocator.class)
  interface PersonRequest extends RequestContext {

    Request<Void> save(PersonProxy personProxy);
    
    Request<PersonProxy> getPersonFromNick(String nick);

    Request<Void> update(PersonProxy personProxy);
  }

  PersonRequest personRequest();
}
