package com.clouway.rftwo.server;

import java.util.List;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public interface ProductRepository {

  void save(Product product);

  void update(Product product);

  List<Product> getAllProducts();

}
