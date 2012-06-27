package com.clouway.rftwo.client;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.web.bindery.requestfactory.shared.RequestContext;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public interface AddProductEditor extends Editor<ProductProxy>, IsWidget{


  void setUpDriver(ProductRequestFactory productRequestFactory, AddProductEditor editor);
  RequestContext fillProductProperties(ProductProxy product);
  void showSuccessMessage();

  public interface Presenter{
    void onAddButtonClicked();
  }

  void setPresenter(Presenter presenter);
}
