package stockwatcher.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import stockwatcher.client.DelistedException;
import stockwatcher.client.StockPrice;
import stockwatcher.client.StockPriceService;

import java.util.Random;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class StockPriceServiceImpl extends RemoteServiceServlet implements StockPriceService{
  private static final double MAX_PRICE = 100.0; // $100.00
  private static final double MAX_PRICE_CHANGE = 0.02; // +/-2%
  
  @Override
  public StockPrice[] getPrices(String[] symbols) throws DelistedException{

    Random rnd = new Random();
    double price;
    double change;
    
    StockPrice[] prices = new StockPrice[symbols.length];
    for(int i=0; i<symbols.length; i++){

      if(symbols[i].equals("ERR")){
        throw new DelistedException("ERR");
      }

      price = rnd.nextDouble() * MAX_PRICE;
      change = price * MAX_PRICE_CHANGE * (rnd.nextDouble() * 2f - 1f);

      prices[i] = new StockPrice(symbols[i], price, change);
    }
    return prices;
  }
}
