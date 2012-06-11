package com.onlinebank.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class UserPanelViewImpl extends Composite implements UserPanelView {

  private Presenter presenter;

  public void setPresenter(Presenter presenter) {
    this.presenter = presenter;
  }

  @UiTemplate("UserPanelView.ui.xml")
  interface UserPanelViewUiBinder extends UiBinder<HTMLPanel, UserPanelViewImpl> {
  }

  @UiField
  Label balanceLabel;
  @UiField
  Label statusMessageLabel;
  @UiField
  Button depositButton;
  @UiField
  Button withdrawButton;
  @UiField
  TextBox withdrawTextBox;
  @UiField
  TextBox depositTextBox;

  private static UserPanelViewUiBinder ourUiBinder = GWT.create(UserPanelViewUiBinder.class);

  public UserPanelViewImpl() {
    initWidget(ourUiBinder.createAndBindUi(this));

  }

  @UiHandler("depositButton")
  void onDepositButtonClicked(ClickEvent event) {
    if (presenter != null) {
      presenter.onDepositButtonClicked();
    }
  }

  @UiHandler("withdrawButton")
  void onWithdrawButtonClicked(ClickEvent event) {
    if (presenter != null) {
      presenter.onWithdrawButtonClicked();
    }
  }

  @Override
  public void setBalanceShown(String text) {
    balanceLabel.setText("Balance " + text + " $");
  }

  @Override
  public void setStatusMessage(String text) {
    statusMessageLabel.setText(text);
  }

  @Override
  public String getWithdrawTextBoxText() {
    return withdrawTextBox.getText();
  }

  @Override
  public String getDepositTextBoxText() {
    return depositTextBox.getText();
  }
}