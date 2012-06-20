package com.clouway.sampleRF.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class EditPersonViewImpl extends Composite implements EditPersonView {
  private Presenter presenter;

  interface EditPersonFormImplUiBinder extends UiBinder<HTMLPanel, EditPersonViewImpl> {
  }

  private static EditPersonFormImplUiBinder ourUiBinder = GWT.create(EditPersonFormImplUiBinder.class);

  @UiField
  Label nameLabel;
  @UiField
  Button editButton;

  public EditPersonViewImpl() {
    initWidget(ourUiBinder.createAndBindUi(this));

  }

  @Override
  public void setPresenter(Presenter presenter) {
    this.presenter = presenter;
  }

  @Override
  public void setPersonForEdit(String name) {
    nameLabel.setText(name);
  }

  @UiHandler("editButton")
  public void onEditButtonClicked(ClickEvent event){
    if (presenter != null) {
      presenter.onEditButtonClicked();
    }
  }
}