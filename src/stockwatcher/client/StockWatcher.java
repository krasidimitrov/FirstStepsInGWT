package stockwatcher.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import java.util.ArrayList;
import java.util.Date;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class StockWatcher implements EntryPoint {


  private FlexTable stockTable = new FlexTable();
  private Button addButton = new Button("Add");
  private TextBox inputBox = new TextBox();
  private Label lastUpdatedDateLabel = new Label("");
  private HorizontalPanel addPanel = new HorizontalPanel();
  private VerticalPanel mainPanel = new VerticalPanel();
  private static final int REFRESH_INTERVAL = 5000;
  private Button removeAllButton = new Button("Remove All");

  private Label errorMsgLabel = new Label();

  private ArrayList<String> stocks = new ArrayList<String>();
  private StockPriceServiceAsync stockPriceSvc = GWT.create(StockPriceService.class);


  public void onModuleLoad() {

    System.out.println("STOCKWATCHER STARTED");

    //Set the columns of the table
    stockTable.setText(0, 0, "Symbol");
    stockTable.setText(0, 1, "Price");
    stockTable.setText(0, 2, "Change");
    stockTable.setText(0, 3, "Remove");
    stockTable.getRowFormatter().addStyleName(0, "watchListHeader");
    stockTable.addStyleName("watchList");
    stockTable.getCellFormatter().addStyleName(0, 1, "watchListNumericColumn");
    stockTable.getCellFormatter().addStyleName(0, 2, "watchListNumericColumn");
    stockTable.getCellFormatter().addStyleName(0, 3, "watchListRemoveColumn");

    //Add elements to the addPanel
    addPanel.add(inputBox);
    addPanel.add(addButton);
    addPanel.add(removeAllButton);
    addPanel.addStyleName("addPanel");

    removeAllButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        for (int i = stocks.size(); i > 0; i--) {
          stockTable.removeRow(i);
        }
        stocks.clear();
      }
    });

    //Add elements to the main panel
    mainPanel.add(errorMsgLabel);
    mainPanel.add(stockTable);
    mainPanel.add(addPanel);
    mainPanel.add(lastUpdatedDateLabel);

    //Add the mainPanel to the RootPanel
    RootPanel.get("stockList").add(mainPanel);


    //Set the cursor focus to the textArea
    inputBox.setFocus(true);

    Timer refreshTimer = new Timer() {
      @Override
      public void run() {
        refreshWatchList();
      }
    };
    refreshTimer.scheduleRepeating(REFRESH_INTERVAL);

    //Add clickHandler for the add button
    addButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        addStock();
      }
    });

    inputBox.addKeyPressHandler(new KeyPressHandler() {
      @Override
      public void onKeyPress(KeyPressEvent event) {
        if (event.getCharCode() == KeyCodes.KEY_ENTER) {
          addStock();
        }
      }
    });
  }

  private void refreshWatchList() {
    //Initiate the service proxy.
    if (stockPriceSvc == null) {
      stockPriceSvc = GWT.create(StockPriceService.class);
    }

    //Set up the callback object.
    AsyncCallback<StockPrice[]> callback = new AsyncCallback<StockPrice[]>() {
      @Override
      public void onFailure(Throwable caught) {
        String details = caught.getMessage();
        if (caught instanceof DelistedException) {
          details = "Company '" + (((DelistedException) caught).getSymbol() + "' was delisted");
        }

        errorMsgLabel.setText("Error: " + details);
        errorMsgLabel.setVisible(true);
      }

      @Override
      public void onSuccess(StockPrice[] result) {
        updateTable(result);
      }
    };

    stockPriceSvc.getPrices(stocks.toArray(new String[0]), callback);
  }

  private void updateTable(StockPrice[] prices) {
    for (StockPrice price : prices) {
      updateTable(price);
    }
    //Display timestamp showing last refresh.
    lastUpdatedDateLabel.setText("Last update: " + new Date().toString());

    //Clear any errors.
    errorMsgLabel.setVisible(false);
  }

  private void updateTable(StockPrice price) {
    // Make sure the stock is still in the stock table.
    if (!stocks.contains(price.getSymbol())) {
      return;
    }

    int row = stocks.indexOf(price.getSymbol()) + 1;

    // Format the data in the Price and Change fields.
    String priceText = NumberFormat.getFormat("#,##0.00").format(price.getPrice());
    NumberFormat changeFormat = NumberFormat.getFormat("+#,##0.00;-#,##0.00");
    String changeText = changeFormat.format(price.getChange());
    String changePercentText = changeFormat.format(price.getChangePercent());

    // Populate the Price and Change fields with new data.
    stockTable.setText(row, 1, priceText);
    Label changeWidget = (Label) stockTable.getWidget(row, 2);
    changeWidget.setText(changeText + " (" + changePercentText + "%)");

    // Change the color of text in the Change field based on its value.
    String changeStyleName = "noChange";
    if (price.getChangePercent() < -0.1f) {
      changeStyleName = "negativeChange";
    } else if (price.getChangePercent() > 0.1f) {
      changeStyleName = "positiveChange";
    }

    changeWidget.setStyleName(changeStyleName);
  }

  private void addStock() {
    final String symbol = inputBox.getText().toUpperCase().trim();
    inputBox.setFocus(true);

    if (!symbol.matches("^[0-9A-Z\\.]{1,10}$")) {
      Window.alert("'" + symbol + "' is not a valid symbol.");
      inputBox.selectAll();
      return;
    }

    if (stocks.contains(symbol)) {
      return;
    }

    int row = stockTable.getRowCount();
    stockTable.setWidget(row, 2, new Label());
    stockTable.getCellFormatter().addStyleName(row, 1, "watchListNumericColumn");
    stockTable.getCellFormatter().addStyleName(row, 2, "watchListNumericColumn");
    stockTable.getCellFormatter().addStyleName(row, 3, "watchListRemoveColumn");

    stocks.add(symbol);
    stockTable.setText(row, 0, symbol);
    Button removeStockButton = new Button("x");
    removeStockButton.addStyleDependentName("remove");

    removeStockButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        int removedIndex = stocks.indexOf(symbol);
        stocks.remove(removedIndex);
        stockTable.removeRow(removedIndex + 1);
      }
    });
    stockTable.setWidget(row, 3, removeStockButton);


    //Get the stock price
    refreshWatchList();
    inputBox.setText("");
  }

}
