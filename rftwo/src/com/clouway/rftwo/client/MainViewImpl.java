package com.clouway.rftwo.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
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

  private AddProductView addProductView;
  private SellProductView sellProductView;
  private ProductEditorImpl productEditor;
  private EventBus eventBus;
  private ProductRequestFactory requestFactory;

  public MainViewImpl(AddProductView addProductView, SellProductView sellProductView, ProductEditorImpl productEditor) {
    initWidget(ourUiBinder.createAndBindUi(this));
      this.addProductView = addProductView;
      this.sellProductView = sellProductView;
      this.productEditor = productEditor;
    eventBus = new SimpleEventBus();
    requestFactory = GWT.create(ProductRequestFactory.class);
    requestFactory.initialize(eventBus);

    new AddProductPresenter(requestFactory , addProductView);
    activeEditor = (Widget) addProductView;
    mainPanel.add(activeEditor);



  }


  @UiHandler("addButton")
  public void onAddButtonClicked(ClickEvent event) {
    changeEditor((Widget) addProductView,new AddProductPresenter(requestFactory, addProductView));
  }

  @UiHandler("sellButton")
  public void onSellByttonClicked(ClickEvent event) {
    changeEditor((Widget) sellProductView, new SellProductPresenter(requestFactory, sellProductView));

  }

//  @UiHandler("editButton")
//  public void onEditButtonClicked(ClickEvent event){
//    changeEditor(productEditor);
//  }


  private void changeEditor(Widget newActiveEditor, Presenter presenter) {
    mainPanel.remove(activeEditor);
    activeEditor = newActiveEditor;
    mainPanel.add(activeEditor);
  }

}