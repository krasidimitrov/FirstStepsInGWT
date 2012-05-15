package com.uibindingexample.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class MyBinderWidget extends Composite{
  

  interface MyBinderWidgetUiBinder extends UiBinder<HTMLPanel, MyBinderWidget> {
  }

  private static MyBinderWidgetUiBinder ourUiBinder = GWT.create(MyBinderWidgetUiBinder.class);

  @UiField
  VerticalPanel myPanelContent;

  public MyBinderWidget() {
   // HTMLPanel rootElement = ourUiBinder.createAndBindUi(this);


    initWidget(ourUiBinder.createAndBindUi(this));
    HTML html1 = new HTML();
    html1.setHTML("<a href='http://www.google.com'>Click me!</a>");
    myPanelContent.add(html1);
    HTML html2 = new HTML();
    html2.setHTML("This is my sample <b>content</b>");
    myPanelContent.add(html2);
  }
}