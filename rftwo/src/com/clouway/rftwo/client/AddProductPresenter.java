package com.clouway.rftwo.client;

import java.math.BigDecimal;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class AddProductPresenter implements AddProductView.Presenter, Presenter {


  private AddProductView addProductView;
  private ProductRequestFactory requestFactory;
  private ProductProxy productForAdd;
  private ProductRequest productRequest;

  public AddProductPresenter(ProductRequestFactory requestFactory, AddProductView addProductView) {
    this.requestFactory = requestFactory;

    this.addProductView = addProductView;
    this.addProductView.setUpDriver(this.requestFactory);
    this.addProductView.setPresenter(this);
    productRequest = requestFactory.productRequest();
    productForAdd = productRequest.create(ProductProxy.class);
    productForAdd.setName("");
    productForAdd.setQuantity(0);
    productForAdd.setPrice(new BigDecimal(0));
    this.addProductView.fillForm(productRequest, productForAdd);
  }


  @Override
  public void onAddButtonClicked() {
    addProduct();
  }

  public void addProduct(){
//    productForAdd = productRequest.edit(productForAdd);
    addProductView.fillProductProperties();
//    productRequest.save(productForAdd).to(new Receiver<Void>() {
//      @Override
//      public void onSuccess(Void response) {
//        addProductView.showSuccessMessage();
//        Window.alert(productForAdd.getName());
//      }
//    }).fire();
  }
  
}
