package com.clouway.rftwo.server;

import java.math.BigDecimal;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class Product {
  
  private Long id;
  private Integer version;
  private String name;
  private BigDecimal price;
  private int quantity;

  public Long getId() {
    return id;
  }

  public Integer getVersion() {
    return version;
  }
}
