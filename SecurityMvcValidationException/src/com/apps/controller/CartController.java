package com.apps.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.apps.beans.CustomerDetails;
import com.apps.beans.Product;
import com.apps.beans.ProductInfo;
import com.apps.beans.UserOrders;
import com.apps.dao.AccountDAO;
import com.apps.service.OrderService;
import com.apps.validation.CustomerValidate;
import com.apps.validation.ProductInfoValidator;

@Controller
public class CartController {
	@Autowired(required=true)
	private AccountDAO accountDAO;
	@Autowired(required=true)
	private OrderService orderService;
	
	@Autowired
	private ProductInfoValidator ProductInfoValidator;
	@Autowired
	private CustomerValidate customerValidate;

	@InitBinder
	public void myInitBinder(WebDataBinder dataBinder) {
		if(dataBinder!=null){
		Object target = dataBinder.getTarget();
		dataBinder.registerCustomEditor(byte[].class,
				new ByteArrayMultipartFileEditor());
		if (target.getClass() == null) {
			return;
		}
		if (target.getClass() == ProductInfo.class) {
			dataBinder.setValidator(ProductInfoValidator);

		} else if (target.getClass() == CustomerDetails.class) {
			dataBinder.setValidator(customerValidate);
		}
		}
	}

	@RequestMapping("/")
	public String home() {
		return "index";
	}

	@RequestMapping("/403")
	public String accessDenied() {
		return "/403";
	}

	@RequestMapping(value={"/login"},method=RequestMethod.GET)
	public String login(Model model) {

		return "login";
	}

	@RequestMapping(value = { "/accountInfo" }, method = RequestMethod.GET)
	public String accountInfo(Model model) {

		UserDetails userDetails = (UserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		model.addAttribute("userDetails", userDetails);
		return "accountInfo";
	}

	// save products
	@RequestMapping(value = { "/product" }, method = RequestMethod.POST)
	@Transactional(propagation = Propagation.NEVER)
	public String productSave(Model model,
			@ModelAttribute("productForm") @Validated ProductInfo productInfo,
			BindingResult result, final RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
		
			return "ProductCreate";
		}
		try {
			accountDAO.save(productInfo);
		} catch (Exception e) {
			String message = e.getMessage();
			model.addAttribute("message", message);
			return "ProductCreate";
		}
		return "ProductList";
	}

	/**
	 * To create and update product
	 * 
	 * @param model
	 * @param code
	 * @return
	 */
	@RequestMapping(value = { "/product" }, method = RequestMethod.GET)
	public String product(Model model,
			@RequestParam(value = "code", defaultValue = "") String code) {
		ProductInfo productInfo = null;
		if (code != null && code.length() > 0) {
			productInfo = accountDAO.findProductInfo(code);
		}
		if (productInfo == null) {
			productInfo = new ProductInfo();
			model.addAttribute("productForm", productInfo);
			productInfo.setNewProduct(true);
			return "ProductCreate";
		}
		model.addAttribute("productForm", productInfo);
		return "ProductEdit";
	}

	/**
	 * To fetch products list
	 * 
	 * @param model
	 * @param name
	 * @return
	 */
	@RequestMapping(value = { "/productList" })
	public String getProductList(Model model,
			@RequestParam(value = "name", defaultValue = "") String name) {
		List<Product> productList = new ArrayList<Product>();
		productList = accountDAO.queryProductList(name);
		model.addAttribute("productList", productList);
		return "ProductList";
	}

	/**
	 * To show image in pages
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param code
	 * @throws IOException
	 */
	@RequestMapping(value = { "/productImage" }, method = RequestMethod.GET)
	public void productImage(HttpServletRequest request,
			HttpServletResponse response, Model model,
			@RequestParam("code") String code) throws IOException {
		Product product = new Product();
		if (code != null) {
			product = (Product) accountDAO.findProduct(code);
		}
		if (product != null && product.getImage() != null) {
			response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
			response.getOutputStream().write(product.getImage());
		}
		response.getOutputStream().close();
	}

	/**
     * 
     */
	@RequestMapping(value = { "buyProduct" }, method = RequestMethod.GET)
	public String showBuyProduct(Model model, @RequestParam("code") String code) {
		Product product = new Product();
		if (code != null) {
			product = (Product) accountDAO.findProduct(code);
		}
		model.addAttribute("productData", product);
		model.addAttribute("productCode", code);
		model.addAttribute("updateflag", "false");
		return "BuyProductView";
	}

	@RequestMapping(value = "/Customerview", method = RequestMethod.GET)
	public String customerDetails(Model model, @RequestParam("code") String code,
											    @RequestParam("price") String price) {
		CustomerDetails customerData = new CustomerDetails();
		customerData.setCode(code);
		customerData.setCost(Float.parseFloat(price));
		model.addAttribute("customerdata", customerData);
		return "CustomerDetails";
	}

	@RequestMapping(value = "/customersave", method = RequestMethod.POST)
	public String saveCustomer(
			HttpServletRequest request,
			HttpServletResponse response,
			Model model,
			@ModelAttribute("customerdata") @Validated CustomerDetails customerDetails,
			BindingResult result) {
		Product product = new Product();
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	      String name = auth.getName();
		String message = "";
		if (result.hasErrors()) {
			return "CustomerDetails";
		}
		customerDetails.setUsername(name);
		boolean status = accountDAO.saveCustomer(customerDetails);
		if (status) {
			message = "Customer inserted sucessfully.";
		}
		model.addAttribute("message", message);
		model.addAttribute("updateflag", "true");
		return "CustomerDetails";
	}
	
	
	@RequestMapping(value={"/shoppingcart"},method=RequestMethod.GET)
	public String showCart(Model model){
		BigDecimal subTotal=new BigDecimal("0.00");
		//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    //String userName = auth.getName();
		List<Object[]> cartData=orderService.fetchCart("");
		for (int i = 0; i < cartData.size(); i++) {
			Object[] obj = cartData.get(i);
			BigDecimal quantity=(BigDecimal) obj[5];
			subTotal=subTotal.add(((BigDecimal) obj[2]).multiply(quantity));
		}
		model.addAttribute("cartList", cartData);
		model.addAttribute("subtotal", subTotal);
		return "cartDisplay";
	}
	
	@RequestMapping(value={"deletecart"},method=RequestMethod.GET)
	public String deleteCart(Model model,@RequestParam("productid") String cartId){
		BigDecimal subTotal=new BigDecimal("0.00");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String userName = auth.getName();
		List<Object[]> cartData=orderService.fetchCart(userName);
		for (int i = 0; i < cartData.size(); i++) {
			Object[] obj = cartData.get(i);
			subTotal=subTotal.add( (BigDecimal) obj[2]);
		}
		boolean status=orderService.deleteCartItem(cartId);
		if(status){
			model.addAttribute("cartList", cartData);
			model.addAttribute("subtotal", subTotal);
			return "cartDisplay";
		}
		return "cartDisplay";
	}
	
	@RequestMapping(value={"/deleteproduct"},method=RequestMethod.GET)
	public String deleteProduct(Model model,@RequestParam("code") String productCode){
		List<Product> productList = new ArrayList<Product>();
		boolean status=accountDAO.deleteProduct(productCode);
		if(status){
			productList = accountDAO.queryProductList("");
			model.addAttribute("productList", productList);
		}
		return "ProductList";
	}
	
	@RequestMapping(value={"/insertorders"},method=RequestMethod.POST)
	public String saveOrders(Model model,@ModelAttribute("productData") Product products){
		@SuppressWarnings("unused")
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String userName = auth.getName();
		UserOrders order=new UserOrders();
		order.setAmount((float) products.getCost());
		order.setNumber(products.getCode());
		order.setUsername(userName);
		order.setQuantity(products.getQuantity());
		List<Product> productList = new ArrayList<Product>();
		boolean status=orderService.saveOrders(order);
		model.addAttribute("customerdata",  new CustomerDetails());
		return "CustomerDetails";
	}
}
