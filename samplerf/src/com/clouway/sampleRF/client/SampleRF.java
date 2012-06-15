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

    final EventBus eventBus = new SimpleEventBus();
    FooRequestFactory requestFactory = GWT.create(FooRequestFactory.class);
    requestFactory.initialize(eventBus);
    SimpleViewImpl simpleView = new SimpleViewImpl(requestFactory);
    RootPanel.get().add(simpleView);


  }
}
