package com.clouway.rftwo.server;

import com.google.inject.Inject;

import java.util.List;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class ProductRepositoryImpl implements ProductRepository{

  @Inject
  private DatabaseHelper databaseHelper;

//  public ProductRepositoryImpl (DatabaseHelper databaseHelper){
//    this.databaseHelper  = databaseHelper;
//  }


  @Override
  public void save(Product product) {
    databaseHelper.executeQuery("INSERT INTO Products(productName, quantity, price) VALUES(?, ?, ?);", product.getName(), product.getQuantity(), product.getPrice());
  }

  @Override
  public void update(Product product) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public List<Product> getAllProducts() {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }
}
