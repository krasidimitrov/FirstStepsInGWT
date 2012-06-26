package com.clouway.rftwo.server;

import com.google.web.bindery.requestfactory.shared.Locator;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class ProductLocator extends Locator<Product, Long> {


  @Override
  public Product create(Class<? extends Product> clazz) {
    try {
      return Product.class.newInstance();
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public Product find(Class<? extends Product> clazz, Long id) {
    return null;
  }

  @Override
  public Class<Product> getDomainType() {
    return Product.class;
  }

  @Override
  public Long getId(Product domainObject) {
    return domainObject.getId();
  }

  @Override
  public Class<Long> getIdType() {
    return Long.class;
  }

  @Override
  public Object getVersion(Product domainObject) {
    return domainObject.getVersion();
  }
}
