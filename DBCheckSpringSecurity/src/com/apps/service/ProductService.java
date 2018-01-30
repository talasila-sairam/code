package com.apps.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apps.BO.FromBO;
import com.apps.BO.Product;
import com.apps.BO.ProductForm;
import com.apps.BO.ToBO;
import com.apps.dao.UserDao;

@Service
public class ProductService {
	@Autowired
	private UserDao userDao;
	public String saveProduct(ProductForm productForm) {
		FromBO fromBo=null;
		ToBO toBo=null;
		Product productBo=null;
		productBo=new Product();
		productBo.setPname(productForm.getPname());
		productBo.setPprice(productForm.getPprice());
		productBo.setPweight(productForm.getPweight());
		
		fromBo=new FromBO();
		fromBo.setSource(productForm.getSource());
		fromBo.setFmobile(productForm.getFaddress());
		fromBo.setFaddress(productForm.getFaddress());
		
		toBo=new ToBO();
		toBo.setDestination(productForm.getDestination());
		toBo.setTaddress(productForm.getTaddress());
		toBo.setTmobile(productForm.getTmobile());
		productBo.setFromBo(fromBo);
		productBo.setToBO(toBo);
		System.out.println(productBo);
		return userDao.saveUser(productBo);
	}

}
