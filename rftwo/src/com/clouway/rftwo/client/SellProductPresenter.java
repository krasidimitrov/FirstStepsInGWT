package com.clouway.rftwo.client;

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
    getAllProductNames();
//    sellProductView.setProductList(listOfProducts);
  }
  
  
  
  @Override
  public void onSellButtonClicked() {
//        sellProductView.setProductList(listOfProducts);
  }
  
  
  private void getAllProductNames(){

    ProductRequest productRequest = requestFactory.productRequest();
    productRequest.getAllProducts().to(new Receiver<List<ProductProxy>>() {
      @Override
      public void onSuccess(List<ProductProxy> response) {
        listOfProducts = response;
        sellProductView.setProductList(response);
      }
    }).fire();
//    sellProductView.setProductList(listOfProducts);
  }

}
