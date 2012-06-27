package com.clouway.rftwo.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

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
  Button editButton;
  @UiField
  HTMLPanel mainPanel;

  private Widget activeEditor;

  private AddProductEditor addProductEditor;
  private SellProductEditorImpl sellProductEditor;
  private ProductEditorImpl productEditor;


  public MainViewImpl(AddProductEditor addProductEditor, SellProductEditorImpl sellProductEditor, ProductEditorImpl productEditor) {
    initWidget(ourUiBinder.createAndBindUi(this));
      this.addProductEditor = addProductEditor;
      this.sellProductEditor = sellProductEditor;
      this.productEditor = productEditor;
//    addProductEditor = new AddProductEditorImpl();
//    sellProductEditor = new SellProductEditorImpl();
//    productEditor = new ProductEditorImpl();
    activeEditor = (Widget) addProductEditor;
    mainPanel.add(activeEditor);


  }


  @UiHandler("addButton")
  public void onAddButtonClicked(ClickEvent event) {
    changeEditor((Widget)addProductEditor);
  }

  @UiHandler("sellButton")
  public void onSellByttonClicked(ClickEvent event) {
    changeEditor(sellProductEditor);

  }

  @UiHandler("editButton")
  public void onEditButtonClicked(ClickEvent event){
    changeEditor(productEditor);
  }


  private void changeEditor(Widget newActiveEditor) {
    mainPanel.remove(activeEditor);
    activeEditor = newActiveEditor;
    mainPanel.add(activeEditor);
  }

}