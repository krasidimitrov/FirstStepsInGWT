package com.clouway.rftwo.client;

import java.util.List;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public interface SellProductView {

  void setProductList(List<ProductProxy> allProductNames);
  ProductProxy getSelectedProduct();
  int getQuantityForSell();

  public interface Presenter{
    void onSellButtonClicked();
  }

  void setPresenter(Presenter presenter);
}
