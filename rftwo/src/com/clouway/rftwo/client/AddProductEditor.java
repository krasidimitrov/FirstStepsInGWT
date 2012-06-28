package com.clouway.rftwo.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.TextBox;

import java.math.BigDecimal;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class AddProductEditor extends Composite implements Editor<ProductProxy>{
  interface AddProductEditorUiBinder extends UiBinder<HTMLPanel, AddProductEditor> {
  }

  private static AddProductEditorUiBinder ourUiBinder = GWT.create(AddProductEditorUiBinder.class);

  @UiField
  TextBox name;
  @Ignore
  @UiField
  TextBox quantityField;
  @Ignore
  @UiField
  TextBox priceField;

  LeafValueEditor<Integer> quantity = new LeafValueEditor<Integer>() {
    @Override
    public void setValue(Integer value) {
      quantityField.setText(String.valueOf(value));
    }

    @Override
    public Integer getValue() {
      return Integer.valueOf(quantityField.getText()); 
    }
  };

  LeafValueEditor<BigDecimal> price = new LeafValueEditor<BigDecimal>() {
    @Override
    public void setValue(BigDecimal value) {
      priceField.setText(String.valueOf(value));
    }

    @Override
    public BigDecimal getValue() {
      return new BigDecimal(priceField.getText());
    }
  };
  
  public AddProductEditor() {
    initWidget(ourUiBinder.createAndBindUi(this));
  }
}