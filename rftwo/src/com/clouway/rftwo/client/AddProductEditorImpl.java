package com.clouway.rftwo.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class AddProductEditorImpl extends Composite{
  interface AddProductEditorImplUiBinder extends UiBinder<HTMLPanel, AddProductEditorImpl> {
  }

  private static AddProductEditorImplUiBinder ourUiBinder = GWT.create(AddProductEditorImplUiBinder.class);

  public AddProductEditorImpl() {
    initWidget(ourUiBinder.createAndBindUi(this));

  }
}