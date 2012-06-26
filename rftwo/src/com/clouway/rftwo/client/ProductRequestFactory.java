package com.clouway.rftwo.client;


import com.google.web.bindery.requestfactory.shared.RequestFactory;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public interface ProductRequestFactory extends RequestFactory {
  
  ProductRequest productRequest();
}
