package com.apps.dao;

import java.util.List;

import com.apps.beans.Account;
import com.apps.beans.CustomerDetails;
import com.apps.beans.Product;
import com.apps.beans.ProductInfo;

public interface AccountDAO {
	public Account findAccount(String userName );

	public void save(ProductInfo productInfo);

	public Product findProduct(String code);

	public ProductInfo findProductInfo(String code);

	public List<Product> queryProductList(String name);

	public boolean saveCustomer(CustomerDetails customerDetails);

	public boolean deleteProduct(String productCode);
}
