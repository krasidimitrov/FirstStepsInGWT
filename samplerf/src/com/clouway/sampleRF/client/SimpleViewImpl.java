package com.clouway.sampleRF.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.web.bindery.requestfactory.shared.Receiver;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class SimpleViewImpl extends Composite{
  private final FooRequestFactory requestFactory;

  interface SimpleViewImplUiBinder extends UiBinder<HTMLPanel, SimpleViewImpl> {
  }

  private static SimpleViewImplUiBinder ourUiBinder = GWT.create(SimpleViewImplUiBinder.class);

  
  @UiField
  Button savePersonButton;
  @UiField
  TextBox nameTextBox;
  @UiField
  TextBox nickTextBox;

  public SimpleViewImpl(FooRequestFactory requestFactory) {
    this.requestFactory = requestFactory;
    initWidget(ourUiBinder.createAndBindUi(this));

  }
  
  
  
  @UiHandler("savePersonButton")
  public void onSaveButtonClicked(ClickEvent event){
    String name = nameTextBox.getName();
    String nick = nickTextBox.getName();
    requestFactory.personRequest().save(name, nick).fire(new Receiver<Void>() {
      @Override
      public void onSuccess(Void response) {
        Window.alert("EMPLOYEE SAVED");
      }
    });
  }
}