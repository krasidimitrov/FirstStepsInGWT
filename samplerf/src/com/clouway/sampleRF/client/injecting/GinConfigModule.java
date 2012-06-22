package com.clouway.sampleRF.client.injecting;

import com.clouway.sampleRF.client.PersonRequestFactory;
import com.clouway.sampleRF.client.SimpleView;
import com.clouway.sampleRF.client.SimpleViewImpl;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class GinConfigModule extends AbstractGinModule{
  @Override
  protected void configure() {
    bind(EventBus.class).to(SimpleEventBus.class);
    bind(SimpleView.class).to(SimpleViewImpl.class);
  }

  @Provides
  @Singleton
  PersonRequestFactory getPersonRequestFactory(EventBus eventBus) {
    PersonRequestFactory requestFactory = GWT.create(PersonRequestFactory.class);
    requestFactory.initialize(eventBus);
    return requestFactory;
  }
}
