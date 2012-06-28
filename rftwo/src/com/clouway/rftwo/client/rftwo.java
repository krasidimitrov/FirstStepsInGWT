package com.clouway.rftwo.client;

import com.google.gwt.core.client.EntryPoint;
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
    ProductEditorImpl productEditor = new ProductEditorImpl();

//    AddProductPresenter addProductPresenter = new AddProductPresenter(requestFactory,addProductView);

    MainViewImpl mainView = new MainViewImpl(addProductView, sellProductView, productEditor);
    RootPanel.get().add(mainView);
  }
}
