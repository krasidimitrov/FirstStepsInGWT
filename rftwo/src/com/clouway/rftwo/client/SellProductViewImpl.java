package com.clouway.rftwo.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.i18n.client.HasDirection;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class SellProductViewImpl extends Composite implements SellProductView {
  private Presenter presenter;

  interface SellProductEditorImplUiBinder extends UiBinder<HTMLPanel, SellProductViewImpl> {
  }

  private static SellProductEditorImplUiBinder ourUiBinder = GWT.create(SellProductEditorImplUiBinder.class);

  @UiField
  Label priceLabel;
  @UiField
  ListBox productList;
  @UiField
  TextBox quantity;
  @UiField
  Button sell;
  private Map<String, ProductProxy> productMap = new HashMap<String, ProductProxy>();

  public SellProductViewImpl() {
    initWidget(ourUiBinder.createAndBindUi(this));
    quantity.setDirection(HasDirection.Direction.LTR);
    quantity.setMaxLength(5);
  }

  @UiHandler("productList")
  public void onProductListChange(ChangeEvent event) {
    Window.alert("Changed to " + productList.getItemText(productList.getSelectedIndex()));

  }

  @Override
  public void setProductList(List<ProductProxy> allProductNames) {
    productList.clear();
    productList.addItem("");
    for (ProductProxy allProductName : allProductNames) {
      productMap.put(allProductName.getName(), allProductName);
      productList.addItem(allProductName.getName());
    }
  }

  @Override
  public void setPresenter(Presenter presenter) {
    this.presenter = presenter;
  }

  @UiHandler("sell")
  public void onSellButtonClicked(ClickEvent event) {
    if (presenter != null) {
      presenter.onSellButtonClicked();
    }
  }

  @UiHandler("productList")
  public void onProductListSelectedValueChange(ChangeEvent event) {
   priceLabel.setText("for "+productMap.get(productList.getValue(productList.getSelectedIndex())).getPrice() +" $ a piece");
  }
}