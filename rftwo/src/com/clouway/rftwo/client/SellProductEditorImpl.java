package com.clouway.rftwo.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.i18n.client.HasDirection;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class SellProductEditorImpl extends Composite{
  interface SellProductEditorImplUiBinder extends UiBinder<HTMLPanel, SellProductEditorImpl> {
  }

  private static SellProductEditorImplUiBinder ourUiBinder = GWT.create(SellProductEditorImplUiBinder.class);

  @UiField
  ListBox productList;
  @UiField
  TextBox quantity;

  public SellProductEditorImpl() {
    initWidget(ourUiBinder.createAndBindUi(this));
    quantity.setDirection(HasDirection.Direction.LTR);
    quantity.setMaxLength(5);
    productList.addItem("Tomato");
    productList.addItem("Cucumber");
    productList.addItem("Melon");
  }

  @UiHandler("productList")
  public void onProductListChange(ChangeEvent event){
    Window.alert("Changed to "+productList.getItemText(productList.getSelectedIndex()));

  }
}