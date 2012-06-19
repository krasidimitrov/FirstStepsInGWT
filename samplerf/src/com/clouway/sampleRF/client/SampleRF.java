package com.clouway.sampleRF.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class SampleRF implements EntryPoint {

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {

    EventBus eventBus = new SimpleEventBus();
    PersonRequestFactory requestFactory = GWT.create(PersonRequestFactory.class);
    requestFactory.initialize(eventBus);
    AppController appController = new AppController(requestFactory, eventBus);
    appController.go(RootPanel.get());


  }
}
