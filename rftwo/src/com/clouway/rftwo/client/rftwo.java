package com.clouway.rftwo.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class rftwo implements EntryPoint {

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {

    EventBus eventBus = new SimpleEventBus();
    ProductRequestFactory requestFactory = GWT.create(ProductRequestFactory.class);
    requestFactory.initialize(eventBus);

    AddProductEditor addProductEditor = new AddProductEditorImpl();
    SellProductEditorImpl sellProductEditor = new SellProductEditorImpl();
    ProductEditorImpl productEditor = new ProductEditorImpl();

    AddProductPresenter addProductPresenter = new AddProductPresenter(requestFactory,addProductEditor);

    MainViewImpl mainView = new MainViewImpl(addProductEditor, sellProductEditor, productEditor);
    RootPanel.get().add(mainView);
  }
}
