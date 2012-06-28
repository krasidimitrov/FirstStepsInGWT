package com.clouway.rftwo.client;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public interface AddProductView{


  void setUpDriver(ProductRequestFactory productRequestFactory);
  void fillForm(ProductRequest productRequest, ProductProxy product);
  void fillProductProperties();
  void showSuccessMessage();

  public interface Presenter{
    void onAddButtonClicked();
  }

  void setPresenter(Presenter presenter);
}
