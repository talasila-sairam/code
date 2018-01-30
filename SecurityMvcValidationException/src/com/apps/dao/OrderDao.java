package com.apps.dao;

import java.util.List;

import com.apps.beans.UserOrders;

public interface OrderDao {

	List<Object[]> fetchCart(String userName);

	boolean deleteCartItem(String cartId);

	boolean saveOrders(UserOrders order);

}
