package stockwatcher.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */

@RemoteServiceRelativePath("stockPrices")
public interface StockPriceService extends RemoteService {
  
  StockPrice[] getPrices(String[] symbols) throws DelistedException;
}
