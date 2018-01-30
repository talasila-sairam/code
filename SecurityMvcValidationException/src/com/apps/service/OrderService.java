package com.apps.service;

import java.util.List;

import com.apps.beans.UserOrders;

public interface OrderService {

	List<Object[]> fetchCart(String userName);

	boolean deleteCartItem(String cartId);

	boolean saveOrders(UserOrders order);

}
