package com.clouway.rftwo.client;

import com.clouway.rftwo.server.Product;
import com.clouway.rftwo.server.ProductLocator;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
@ProxyFor(value = Product.class, locator = ProductLocator.class)
public interface ProductProxy {

}
