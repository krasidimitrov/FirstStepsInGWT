package com.clouway.rftwo.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class MainViewImpl extends Composite {
  interface MainViewImplUiBinder extends UiBinder<HTMLPanel, MainViewImpl> {
  }

  private static MainViewImplUiBinder ourUiBinder = GWT.create(MainViewImplUiBinder.class);

  @UiField
  Button addButton;
  @UiField
  Button sellButton;
  @UiField
  HTMLPanel mainPanel;

  AddProductEditorImpl addProductEditor = null;
  SellProductEditorImpl sellProductEditor = null;


  public MainViewImpl() {
    initWidget(ourUiBinder.createAndBindUi(this));

  }


  @UiHandler("addButton")
  public void onAddButtonClicked(ClickEvent event) {
    if(sellProductEditor != null){
      mainPanel.remove(sellProductEditor);
    }
    if (addProductEditor == null) {
      addProductEditor = new AddProductEditorImpl();
    }
    mainPanel.add(addProductEditor);
  }

  @UiHandler("sellButton")
  public void onSellByttonClicked(ClickEvent event){
    if(addProductEditor != null){
      mainPanel.remove(addProductEditor);
    }
    if(sellProductEditor == null){
      sellProductEditor = new SellProductEditorImpl();
    }
      mainPanel.add(sellProductEditor);

  }

}