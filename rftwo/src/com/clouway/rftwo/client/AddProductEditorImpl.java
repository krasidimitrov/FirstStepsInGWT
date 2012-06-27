package com.clouway.rftwo.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.web.bindery.requestfactory.gwt.client.RequestFactoryEditorDriver;
import com.google.web.bindery.requestfactory.shared.RequestContext;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class AddProductEditorImpl extends Composite implements AddProductEditor{


  interface Driver extends
          RequestFactoryEditorDriver<ProductProxy, AddProductEditor> {
  }

  private Driver editorDriver;




  private Presenter presenter;

  @Override
  public void setPresenter(Presenter presenter) {
    this.presenter = presenter;
  }

  interface AddProductEditorImplUiBinder extends UiBinder<HTMLPanel, AddProductEditorImpl> {
  }


  private static AddProductEditorImplUiBinder ourUiBinder = GWT.create(AddProductEditorImplUiBinder.class);

  @UiField
  Button addProductButton;


  public AddProductEditorImpl() {
    initWidget(ourUiBinder.createAndBindUi(this));
    editorDriver = GWT.create(Driver.class);
  }

  @Override
  public void setUpDriver(ProductRequestFactory productRequestFactory, AddProductEditor editor) {
    editorDriver.initialize(productRequestFactory, editor);
  }

  @Override
  public RequestContext fillProductProperties(ProductProxy product) {
    return editorDriver.flush();
  }

  @Override
  public void showSuccessMessage() {
    Window.alert("Product added successfully!");
  }

  @UiHandler("addProductButton")
  public void onAddButtonClicked(ClickEvent event){
    if(presenter != null){
      presenter.onAddButtonClicked();
    }
  }

}