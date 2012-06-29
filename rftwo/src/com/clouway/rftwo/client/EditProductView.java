package com.clouway.rftwo.client;

import java.util.List;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public interface EditProductView {

  void setPresenter(Presenter presenter);

  public void fillProductTable(List<ProductProxy> listOfProducts);

  public interface Presenter{
//    void onSellButtonClicked();
  }
}
