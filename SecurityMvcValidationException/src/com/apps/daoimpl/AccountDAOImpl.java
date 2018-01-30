package com.apps.daoimpl;

import java.util.Date;
import java.util.List;

















import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



import org.springframework.stereotype.Repository;

import com.apps.beans.Account;
import com.apps.beans.CustomerDetails;
import com.apps.beans.Product;
import com.apps.beans.ProductInfo;
import com.apps.beans.UserOrders;
import com.apps.dao.AccountDAO;

@Repository("accountDAO")
public class AccountDAOImpl implements AccountDAO {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Account findAccount(String userName) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction=session.beginTransaction();
		Criteria crit = session.createCriteria(Account.class);
		crit.add(Restrictions.eq("userName", userName));
		Account account=(Account) crit.uniqueResult();
		transaction.commit();
		return account;
	}

	@Override
	public void save(ProductInfo productInfo) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		Transaction transaction=session.beginTransaction();
		String code = productInfo.getCode();
		Product product = null;
		boolean isNew = false;
		if (code != null) {
			product = this.findProduct1(code,session);
		}
		if (product == null) {
			isNew = true;
			product = new Product();
			product.setCreateDate(new Date());
		}
		product.setCode(code);
		product.setName(productInfo.getName());
		product.setCost(productInfo.getPrice());
		if (productInfo.getFileData() != null) {
			byte[] image = productInfo.getFileData().getBytes();
			if (image != null && image.length > 0) {
				product.setImage(image);
			}
		}
		if (isNew) {
			session.save(product);
		}
		sessionFactory.getCurrentSession().flush();
		transaction.commit();
	}
	
	public Product findProduct1(String code,Session session) {
		Criteria crit = session.createCriteria(Product.class);
		crit.add(Restrictions.eq("code", code));
		Product product=(Product) crit.uniqueResult();
		return product;
	}
	
	@Override
	public Product findProduct(String code) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction=session.beginTransaction();
		Criteria crit = session.createCriteria(Product.class);
		crit.add(Restrictions.eq("code", code));
		Product product=(Product) crit.uniqueResult();
		transaction.commit();
		return product;
	}

	@Override
	public ProductInfo findProductInfo(String code) {
		Product product = this.findProduct(code);
		if (product == null) {
			return null;
		}
		return new ProductInfo(product.getCode(), product.getName(),
				product.getCost());
	}

	@Override
	public List<Product> queryProductList(String name) {
		// TODO Auto-generated method stub
		List<Product> productList = null;
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction=session.beginTransaction();
		Criteria crit = session.createCriteria(Product.class); 
		 productList = (List<Product>)crit.list();
		 transaction.commit();
		return productList;
	}

	@Override
	public boolean saveCustomer(CustomerDetails customerDetails) {
		// TODO Auto-generated method stub
		Transaction transaction1=null;
		Transaction transaction=null;
		try{
		Session session=sessionFactory.getCurrentSession();
		transaction=session.beginTransaction();
		CustomerDetails customer=new CustomerDetails();
		customer.setCustomerName(customerDetails.getCustomerName());
		customer.setCustomerAddr(customerDetails.getCustomerAddr());
		customer.setCustomerPhone(customerDetails.getCustomerPhone());
		customer.setUsername(customerDetails.getUsername());
		customer.setAddressType(customerDetails.getAddressType());
		customer.setCode(customerDetails.getCode());
		customer.setCost(customerDetails.getCost());
		customer.setEmail("chandu@gmail.com");
		session.save(customer);	
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		finally{
			
			transaction.commit();
		}
		return true;
	}

	@Override
	public boolean deleteProduct(String productCode) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		Transaction trans=session.beginTransaction();
		Product product=new Product();
		product.setCode(productCode);
		session.delete(product);
		trans.commit();
		return true;
	}
}
