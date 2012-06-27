package com.clouway.rftwo.client;

import com.google.web.bindery.requestfactory.shared.Receiver;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class AddProductPresenter implements AddProductEditor.Presenter {


  private final AddProductEditor addProductEditor;
  private ProductRequestFactory requestFactory;
  private ProductProxy productForAdd;
  private ProductRequest productRequest;

  public AddProductPresenter(ProductRequestFactory requestFactory, AddProductEditor addProductEditor) {
    this.requestFactory = requestFactory;
    this.addProductEditor = addProductEditor;
    addProductEditor.setPresenter(this);
    productRequest = requestFactory.productRequest();
    productForAdd = productRequest.create(ProductProxy.class);
  }


  @Override
  public void onAddButtonClicked() {
    addProduct();
  }

  public void addProduct(){
    productForAdd = productRequest.edit(productForAdd);
    ProductRequest request = (ProductRequest) addProductEditor.fillProductProperties(productForAdd);
    request.save(productForAdd).to(new Receiver<Void>() {
      @Override
      public void onSuccess(Void response) {
        addProductEditor.showSuccessMessage();
      }
    }).fire();
  }
  
}
