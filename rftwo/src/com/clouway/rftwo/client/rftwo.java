package com.clouway.rftwo.client;

import com.clouway.rftwo.client.gin.SimpleGinInjector;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class rftwo implements EntryPoint {

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {

//    EventBus eventBus = new SimpleEventBus();
//    ProductRequestFactory requestFactory = GWT.create(ProductRequestFactory.class);
//    requestFactory.initialize(eventBus);

    AddProductView addProductView = new AddProductViewImpl();
    SellProductView sellProductView = new SellProductViewImpl();
    EditProductViewImpl productView = new EditProductViewImpl();

//    AddProductPresenter addProductPresenter = new AddProductPresenter(requestFactory,addProductView);
    SimpleGinInjector ginInjector = GWT.create(SimpleGinInjector.class);
    MainViewImpl mainView = ginInjector.getMainViewImpl();
//    MainViewImpl mainView = new MainViewImpl();//addProductView, sellProductView, productView);

    RootPanel.get().add(mainView);
  }
}
