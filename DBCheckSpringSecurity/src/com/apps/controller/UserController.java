package com.apps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.ModelAndView;

import com.apps.BO.ProductForm;
import com.apps.service.ProductService;
import com.apps.validaters.ProductValidator;

@Controller
public class UserController {
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductValidator productValidator;
	
	@RequestMapping("/home.htm")
	public String loginPage(@RequestParam("error") String failure,Model model) {
		if(failure!="") {
			model.addAttribute("failure", "Invalid user name password");
		}
		return "Login";
	}
	
	@RequestMapping(value="/saveform.htm",method=RequestMethod.POST)
	public String saveProduct(@ModelAttribute("productForm") ProductForm productForm,Model model,BindingResult errors) {
		System.out.println(productForm);
		//String message=productService.saveProduct(productForm);
		if(productForm!=null) {
			productValidator.supports(ProductForm.class);
			productValidator.validate(productForm, errors);
		}
		if(errors.hasErrors()) {
			return "ProductForm";
		}
		return null;
	}
	
	@RequestMapping(value="/saveform.htm",method=RequestMethod.GET)
	public String showProductForm(Model model) {
		model.addAttribute("productForm", new ProductForm());
		return "ProductForm";
	}
}
