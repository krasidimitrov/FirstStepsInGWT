package com.clouway.rftwo.client;

import com.google.web.bindery.requestfactory.shared.Receiver;

import java.util.List;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class EditProductPresenter implements EditProductView.Presenter, Presenter{


  private final ProductRequestFactory requestFactory;
  private final EditProductView editProductView;

  public EditProductPresenter(ProductRequestFactory requestFactory, EditProductView editProductView){
    this.requestFactory = requestFactory;
    this.editProductView = editProductView;
    this.editProductView.setPresenter(this);
    setAllProductNames();
  }



  private void setAllProductNames(){

    ProductRequest productRequest = requestFactory.productRequest();
    productRequest.getAllProducts().to(new Receiver<List<ProductProxy>>() {
      @Override
      public void onSuccess(List<ProductProxy> response) {
        editProductView.fillProductTable(response);
      }
    }).fire();
  }
}
