package com.clouway.rftwo.server;

import com.google.inject.Inject;

import java.util.List;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class ProductService {
   @Inject
   ProductRepository productRepository;


  public void save(Product product){
    productRepository.save(product);
  }

  public List<Product> getAllProducts(){
    return productRepository.getAllProducts();
  }

}
