package com.clouway.sampleRF.client;

import com.google.web.bindery.requestfactory.shared.RequestFactory;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public interface PersonRequestFactory extends RequestFactory{


  //Can it be a class on the value property instead of interface?
//  @Service(value = PersonService.class, locator = MyServiceLocator.class)
//  interface PersonRequest extends RequestContext {
//
//  }

  PersonRequest personRequest();
}
