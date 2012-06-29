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

import java.util.List;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class EditProductViewImpl extends Composite implements EditProductView {
  private Presenter presenter;
  private ListDataProvider<ProductProxy> dataProvider;

  interface ProductEditorImplUiBinder extends UiBinder<HTMLPanel, EditProductViewImpl> {
  }

  private static ProductEditorImplUiBinder ourUiBinder = GWT.create(ProductEditorImplUiBinder.class);

  @Override
  public void setPresenter(Presenter presenter) {
    this.presenter = presenter;
  }

  @UiField
  CellTable<ProductProxy> productCellTable;

  public EditProductViewImpl() {
    initWidget(ourUiBinder.createAndBindUi(this));



    dataProvider = new ListDataProvider<ProductProxy>();

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

  }

  @Override
  public void fillProductTable(List<ProductProxy> listOfProducts) {
    List<ProductProxy> list = dataProvider.getList();
    for (ProductProxy product : listOfProducts) {
      list.add(product);
    }
  }
}