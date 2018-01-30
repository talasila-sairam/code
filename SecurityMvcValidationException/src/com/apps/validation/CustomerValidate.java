package com.apps.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.apps.beans.CustomerDetails;

@Component("customerValidate")
public class CustomerValidate implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return clazz==CustomerDetails.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		CustomerDetails customerDetails=(CustomerDetails) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"customerName","NotEmpty.customerdata.customerName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customerAddr", "NotEmpty.customerdata.customerAddr");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customerPhone", "NotEmpty.customerdata.customerPhone");
	}

}
