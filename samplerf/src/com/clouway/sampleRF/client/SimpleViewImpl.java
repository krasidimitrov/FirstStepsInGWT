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

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class SimpleViewImpl extends Composite implements SimpleView, Editor<PersonProxy> {


  private Presenter presenter;

  interface SimpleViewImplUiBinder extends UiBinder<HTMLPanel, SimpleViewImpl> {
  }

  private static SimpleViewImplUiBinder ourUiBinder = GWT.create(SimpleViewImplUiBinder.class);


  @UiField
  Button savePersonButton;
  @UiField
  Button selectButton;
  @UiField
  TextBox nameTextBox;
  @UiField
  TextBox nickTextBox;
  @UiField
  TextBox phoneTextBox;
  @UiField
  TextBox occupationTextBox;
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
    return nameTextBox.getText();
  }

  @Override
  public String getNickValue() {
    return nickTextBox.getText();
  }

  @Override
  public String getPhoneValue() {
    return phoneTextBox.getText();
  }

  @Override
  public String getOccupationValue() {
    return occupationTextBox.getText();
  }

  @Override
  public void setNickValue(String nick) {
    nickTextBox.setText(nick);
  }

  @Override
  public void setPhoneValue(String phone) {
    phoneTextBox.setText(phone);
  }

  @Override
  public void setOccupationValue(String occupation) {
    occupationTextBox.setText(occupation);
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
      switchButton.setText("turn edit ON");
    } else {
      editButton.setEnabled(true);
      switchButton.setText("turn edit OFF");
    }
  }
}