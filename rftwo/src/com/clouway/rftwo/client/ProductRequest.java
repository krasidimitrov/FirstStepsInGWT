package com.clouway.rftwo.client;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */

import com.clouway.rftwo.inject.MyServiceLocator;
import com.clouway.rftwo.server.ProductService;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

@Service(value = ProductService.class, locator = MyServiceLocator.class)
public interface ProductRequest extends RequestContext{

  Request<Void> save(ProductProxy productProxy);
}
