package com.clouway.rftwo.server;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class ProductRowMapper implements RowMapper<Product>{
  @Override
  public Product map(ResultSet resultSet) throws SQLException {
    Product product = new Product();
    product.setName(resultSet.getString("productName"));
    product.setQuantity(resultSet.getInt("quantity"));
    product.setPrice(resultSet.getBigDecimal("price"));
    return product;
  }
}
