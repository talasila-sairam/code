package com.apps.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.apps.beans.Product;
import com.apps.beans.ProductInfo;
import com.apps.dao.AccountDAO;

@Component("ProductInfoValidator")
public class ProductInfoValidator implements Validator{
	@Autowired
	private AccountDAO accountDAO;
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return clazz==ProductInfo.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ProductInfo productInfo=(ProductInfo)target;
		System.out.println(productInfo);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"code","NotEmpty.productForm.code");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.productForm.name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "NotEmpty.productForm.price");
		if(productInfo.getCode()!=null&&productInfo.getCode().length()>0){
			if(productInfo.getCode().matches("\\s+")){
				errors.rejectValue("code","Pattern.productForm.code");
			}else if (productInfo.isNewProduct()) {
				Product product=accountDAO.findProduct(productInfo.getCode());
				if (product != null) {
                    errors.rejectValue("code", "Duplicate.productForm.code");
                }
			}
		}
	}

}
