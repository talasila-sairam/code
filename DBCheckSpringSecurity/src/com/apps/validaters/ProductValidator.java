package com.apps.validaters;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.apps.BO.ProductForm;

@Component
public class ProductValidator implements Validator{

	@Override
	public boolean supports(Class<?> classType) {
		return classType.isAssignableFrom(ProductForm.class);
	}

	@Override
	public void validate(Object object, Errors errors) {
		ProductForm product=(ProductForm)object;
		if(product.getFmobile()==null||product.getFmobile().length()==0) {
			errors.rejectValue("fmobile", "Please enter all mandatory fields");
		}
		if(product.getTmobile()==null||product.getTmobile().length()==0) {
			errors.rejectValue("tmobile", "Please enter all mandatory fields");
		}
		if(product.getPname()==null||product.getPname().length()==0) {
			errors.rejectValue("pname", "Please enter all mandatory fields");
		}
		if(product.getPprice()<=0) {
			errors.rejectValue("pprice", "Please enter all mandatory fields");
		}
	}

}
