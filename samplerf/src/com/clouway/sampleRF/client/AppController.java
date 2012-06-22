package com.clouway.sampleRF.client;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class AppController implements Presenter, ValueChangeHandler<String>{
  private HasWidgets container;
//  @Inject
  private PersonRequestFactory requestFactory;
//  @Inject
  private EventBus eventBus;
  private SimpleViewImpl simpleView = null;

  public AppController(PersonRequestFactory requestFactory, EventBus eventBus){
    this.requestFactory = requestFactory;
    this.eventBus = eventBus;
    bind();
  }

  public AppController(){
    bind();
  }

  private void bind() {
    History.addValueChangeHandler(this);
  }

  @Override
  public void go(HasWidgets container) {
    this.container = container;

    if ("".equals(History.getToken())) {
      History.newItem("simpleForm");
    } else {
      History.fireCurrentHistoryState();
    }
  }

  @Override
  public void onValueChange(ValueChangeEvent<String> stringValueChangeEvent) {
    String token = stringValueChangeEvent.getValue();

    if (token != null) {
      if (token.equals("simpleForm")) {

        if (simpleView == null) {
          simpleView = new SimpleViewImpl();
        }

        new SimpleFormPresenter(requestFactory, simpleView).go(container);
      }

    }

  }
}
