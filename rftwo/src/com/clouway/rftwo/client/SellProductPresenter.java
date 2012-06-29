package com.clouway.rftwo.client;

import com.google.gwt.user.client.Window;
import com.google.web.bindery.requestfactory.shared.Receiver;

import java.util.List;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class SellProductPresenter implements SellProductView.Presenter, Presenter{


  private final ProductRequestFactory requestFactory;
  private final SellProductView sellProductView;
  private List<ProductProxy> listOfProducts;

  public SellProductPresenter(ProductRequestFactory requestFactory, SellProductView sellProductView){
    this.requestFactory = requestFactory;
    this.sellProductView = sellProductView;
    this.sellProductView.setPresenter(this);
    setAllProductNames();
  }
  
  
  
  @Override
  public void onSellButtonClicked() {
    decreaseProductQuantity();
  }
  
  
  private void setAllProductNames(){

    ProductRequest productRequest = requestFactory.productRequest();
    productRequest.getAllProducts().to(new Receiver<List<ProductProxy>>() {
      @Override
      public void onSuccess(List<ProductProxy> response) {
        listOfProducts = response;
        sellProductView.setProductList(response);
      }
    }).fire();
  }

  public void decreaseProductQuantity(){
    ProductRequest productRequest = requestFactory.productRequest();
    ProductProxy productProxy = sellProductView.getSelectedProduct();
    productProxy = productRequest.edit(productProxy);
    productProxy.setQuantity(productProxy.getQuantity()-sellProductView.getQuantityForSell());
    productRequest.update(productProxy).to(new Receiver<Void>() {
      @Override
      public void onSuccess(Void response) {
        Window.alert("HARDCODED SUCCESS");
      }
    }).fire();
  }

}
