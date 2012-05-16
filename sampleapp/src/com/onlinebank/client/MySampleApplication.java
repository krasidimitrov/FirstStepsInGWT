package com.onlinebank.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;


/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class MySampleApplication implements EntryPoint {
  private Button helloButton;

  /**
   * This is the entry point method.
   */
//  public void onModuleLoad() {
//
//    helloButton = new Button("Hello");
//    helloButton.addClickHandler(new ClickHandler() {
//      @Override
//      public void onClick(ClickEvent event) {
//        Window.alert("Hello user");
//      }
//    });
//
//
//    RootPanel.get().add(helloButton);
//  }


  TabPanel tabPanel;

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    tabPanel = new TabPanel();

    tabPanel.add(new HTML("<h1>Page 0 Content: Llamas</h1>"), " Page 0 ");
    tabPanel.add(new HTML("<h1>Page 1 Content: Alpacas</h1>"), " Page 1 ");
    tabPanel.add(new HTML("<h1>Page 2 Content: Camels</h1>"), " Page 2 ");

    tabPanel.addSelectionHandler(new SelectionHandler<Integer>() {
      public void onSelection(SelectionEvent<Integer> event) {
        History.newItem("page" + event.getSelectedItem());
      }
    });

    History.addValueChangeHandler(new ValueChangeHandler<String>() {
      public void onValueChange(ValueChangeEvent<String> event) {
        String historyToken = event.getValue();

        // Parse the history token
        try {
          if (historyToken.substring(0, 4).equals("page")) {
            String tabIndexToken = historyToken.substring(4, 5);
            int tabIndex = Integer.parseInt(tabIndexToken);
            // Select the specified tab panel
            tabPanel.selectTab(tabIndex);
          } else {
            tabPanel.selectTab(0);
          }

        } catch (IndexOutOfBoundsException e) {
          tabPanel.selectTab(0);
        }
      }
    });

    tabPanel.selectTab(0);

    helloButton = new Button("Hello");
    helloButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        alert("HELLOOO");
        Window.alert("BYEEE");
      }
    });

    RootPanel.get().add(tabPanel);
    RootPanel.get().add(helloButton);
  }


  public static native void alert(String msg)/*-{
    $wnd.alert(msg);
  }-*/;


}
