package com.clouway.rftwo.client;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.web.bindery.event.shared.SimpleEventBus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class ProductEditorImpl extends Composite{
  interface ProductEditorImplUiBinder extends UiBinder<HTMLPanel, ProductEditorImpl> {
  }

  private static ProductEditorImplUiBinder ourUiBinder = GWT.create(ProductEditorImplUiBinder.class);

  @UiField
  CellTable<ProductProxy> productCellTable;

  public ProductEditorImpl() {
    initWidget(ourUiBinder.createAndBindUi(this));

    ProductRequestFactory requestFactory = GWT.create(ProductRequestFactory.class);
    requestFactory.initialize(new SimpleEventBus());

    ProductRequest productRequest = requestFactory.productRequest();


    ProductProxy productProxy = productRequest.create(ProductProxy.class);
    productProxy.setName("DSASD");
    productProxy.setPrice(new BigDecimal(10));
    productProxy.setQuantity(10);

    List<ProductProxy> listProducts = new ArrayList<ProductProxy>();
    listProducts.add(productProxy);

    ListDataProvider<ProductProxy> dataProvider = new ListDataProvider<ProductProxy>();

    dataProvider.addDataDisplay(productCellTable);


    Column<ProductProxy, String> name = new Column<ProductProxy, String>(new TextCell()) {
      @Override
      public String getValue(ProductProxy object) {
        return object.getName();
      }
    };

    Column<ProductProxy, String> price = new Column<ProductProxy, String>(new TextCell()) {
      @Override
      public String getValue(ProductProxy object) {
        return String.valueOf(object.getPrice());
      }
    };

    Column<ProductProxy, String> quantity = new Column<ProductProxy, String>(new TextCell()) {
      @Override
      public String getValue(ProductProxy object) {
        return String.valueOf(object.getQuantity());
      }
    };

    productCellTable.addColumn(name, "Name");
    productCellTable.addColumn(price, "Price");
    productCellTable.addColumn(quantity, "Quantity");

    productCellTable.setColumnWidth(name, 200, Style.Unit.PCT);

    List<ProductProxy> list = dataProvider.getList();
    for(ProductProxy product: listProducts){
      list.add(product);
    }

  }
}