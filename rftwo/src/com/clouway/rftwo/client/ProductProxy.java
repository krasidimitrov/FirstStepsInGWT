package com.clouway.rftwo.client;

import com.clouway.rftwo.server.Product;
import com.clouway.rftwo.server.ProductLocator;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

import java.math.BigDecimal;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
@ProxyFor(value = Product.class, locator = ProductLocator.class)
public interface ProductProxy extends EntityProxy{



  
  void setName(String name);
  
  void setPrice(BigDecimal price);
  
  void setQuantity(int quantity);
  
  
  String getName();

  BigDecimal getPrice();
  
  int getQuantity();
}
