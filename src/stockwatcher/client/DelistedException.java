package stockwatcher.client;

import java.io.Serializable;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class DelistedException extends Exception implements Serializable{
  
  private String symbol;
  
  public DelistedException(){
    
  }
  
  public DelistedException(String symbol){
    this.symbol = symbol;
  }
  
  public String getSymbol(){
    return this.symbol;
  }
}
