package stockwatcher.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

public interface StockPriceServiceAsync {
  void getPrices(String[] symbols, AsyncCallback<StockPrice[]> callback);
}
