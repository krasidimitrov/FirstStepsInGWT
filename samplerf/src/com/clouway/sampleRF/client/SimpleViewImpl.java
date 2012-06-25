package com.clouway.sampleRF.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.web.bindery.requestfactory.gwt.client.RequestFactoryEditorDriver;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class SimpleViewImpl extends Composite implements SimpleView, Editor<PersonProxy> {

  interface Driver extends
          RequestFactoryEditorDriver<PersonProxy, SimpleViewImpl> {
  }
  
  private Driver editorDriver;


  private Presenter presenter;

  interface SimpleViewImplUiBinder extends UiBinder<HTMLPanel, SimpleViewImpl> {
  }

  private static SimpleViewImplUiBinder ourUiBinder = GWT.create(SimpleViewImplUiBinder.class);


  @UiField
  Button savePersonButton;
  @UiField
  Button selectButton;
  @UiField
  TextBox name;
  @UiField
  TextBox nick;
  @UiField
  TextBox phone;
  @UiField
  TextBox occupation;
  @UiField
  Button editButton;
  @UiField
  Button switchButton;


  public SimpleViewImpl() {
    initWidget(ourUiBinder.createAndBindUi(this));
    editButton.setEnabled(false);

  }

  @Override
  public void setPresenter(Presenter presenter) {
    this.presenter = presenter;
  }

  @Override
  public String getNameValue() {
    return name.getText();
  }

  @Override
  public String getNickValue() {
    return nick.getText();
  }

  @Override
  public String getPhoneValue() {
    return phone.getText();
  }

  @Override
  public String getOccupationValue() {
    return occupation.getText();
  }

  @Override
  public void setNameValue(String name) {
    this.name.setText(name);
  }

  @Override
  public void setNickValue(String nick) {
    this.nick.setText(nick);
  }

  @Override
  public void setPhoneValue(String phone) {
    this.phone.setText(phone);
  }

  @Override
  public void setOccupationValue(String occupation) {
    this.occupation.setText(occupation);
  }

  @Override
  public void showSuccessMessage() {
    Window.alert("Person Saved Successful!");
  }


  @UiHandler("savePersonButton")
  public void onSaveButtonClicked(ClickEvent event) {
    if (presenter != null) {
      presenter.onSaveButtonClicked();
    }
  }

  @UiHandler("selectButton")
  public void onSelectButtonClicked(ClickEvent event) {
    if (presenter != null) {

      presenter.onSelectButtonClicked();
    }
  }

  @UiHandler("switchButton")
  public void onSwitchButtonClicked(ClickEvent event) {
    if (editButton.isEnabled()) {
      editButton.setEnabled(false);
      nick.setEnabled(true);

      switchButton.setText("turn edit ON");
    } else {
      editButton.setEnabled(true);
      nick.setEnabled(false);
      switchButton.setText("turn edit OFF");
    }
  }

  @UiHandler("editButton")
  public void onEditButtonClicked(ClickEvent event) {
    if(presenter != null){
      presenter.onEditButtonClicked();
    }
  }

  @Override
  public Driver getDriver() {
    editorDriver = GWT.create(Driver.class);
    return editorDriver;  //To change body of implemented methods use File | Settings | File Templates.
  }
}