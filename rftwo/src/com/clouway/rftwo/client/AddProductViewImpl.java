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
import com.google.web.bindery.requestfactory.shared.Receiver;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class AddProductViewImpl extends Composite implements AddProductView {


  interface Driver extends
          RequestFactoryEditorDriver<ProductProxy, AddProductEditor> {
  }

  private Driver editorDriver;




  private Presenter presenter;

  @Override
  public void setPresenter(Presenter presenter) {
    this.presenter = presenter;
  }

  interface AddProductViewImplUiBinder extends UiBinder<HTMLPanel, AddProductViewImpl> {
  }


  private static AddProductViewImplUiBinder ourUiBinder = GWT.create(AddProductViewImplUiBinder.class);

  @UiField
  Button addProductButton;
  @UiField
  AddProductEditor editor;

  public AddProductViewImpl() {
    initWidget(ourUiBinder.createAndBindUi(this));
    editorDriver = GWT.create(Driver.class);
  }

  @Override
  public void setUpDriver(ProductRequestFactory productRequestFactory) {
    editorDriver.initialize(productRequestFactory, editor);
  }

  @Override
  public void fillForm(ProductRequest productRequest, ProductProxy product) {
    productRequest.save(product).to(new Receiver<Void>() {
      @Override
      public void onSuccess(Void response) {
        showSuccessMessage();
      }
    });
    editorDriver.edit(product, productRequest);
  }


  @Override
  public void fillProductProperties() {

    editorDriver.flush().fire();
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