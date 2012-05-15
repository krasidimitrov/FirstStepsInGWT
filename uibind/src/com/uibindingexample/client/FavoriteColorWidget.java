package com.uibindingexample.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import java.util.ArrayList;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class FavoriteColorWidget extends Composite{
  interface FavoriteColorWidgetUiBinder extends UiBinder<Widget, FavoriteColorWidget> {
  }

  private static FavoriteColorWidgetUiBinder ourUiBinder = GWT.create(FavoriteColorWidgetUiBinder.class);

  @UiField
  Label greeting;
  @UiField
  CheckBox red; 
  @UiField
  CheckBox white;
  @UiField
  CheckBox blue;
  @UiField
  Button button;
  
  public FavoriteColorWidget() {
    initWidget(ourUiBinder.createAndBindUi(this));
    
    greeting.setText("Hello Krasi!");
    
    final ArrayList<CheckBox> checkBoxes = new ArrayList<CheckBox>();
    checkBoxes.add(red);
    checkBoxes.add(white);
    checkBoxes.add(blue);

    //add a button handler to show the color when clicked
    button.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        String t = "";
        for(CheckBox box: checkBoxes){
          if(box.getValue()){
            t += box.getFormValue() + ", ";
          }
        }
        Window.alert("Your favorite color/colors are: "+t);
      }
    });
  }
}