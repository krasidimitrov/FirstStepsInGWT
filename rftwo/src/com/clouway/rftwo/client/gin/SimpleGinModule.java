package com.clouway.rftwo.client.gin;

import com.clouway.rftwo.client.AddProductView;
import com.clouway.rftwo.client.AddProductViewImpl;
import com.clouway.rftwo.client.EditProductView;
import com.clouway.rftwo.client.EditProductViewImpl;
import com.clouway.rftwo.client.SellProductView;
import com.clouway.rftwo.client.SellProductViewImpl;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class SimpleGinModule extends AbstractGinModule{
  @Override
  protected void configure() {
    bind(AddProductView.class).to(AddProductViewImpl.class).in(Singleton.class);
    bind(SellProductView.class).to(SellProductViewImpl.class).in(Singleton.class);
    bind(EditProductView.class).to(EditProductViewImpl.class).in(Singleton.class);

  }
}
