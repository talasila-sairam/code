package com.apps.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.apps.beans.UserOrders;
import com.apps.dao.OrderDao;
import com.apps.service.OrderService;
@Service("orderService")
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderDao orderDao;

	@Override
	public List<Object[]> fetchCart(String userName) {
		// TODO Auto-generated method stub
		return orderDao.fetchCart(userName);
	}

	@Override
	public boolean deleteCartItem(String cartId) {
		// TODO Auto-generated method stub
		return orderDao.deleteCartItem(cartId);
	}

	@Override
	public boolean saveOrders(UserOrders order) {
		// TODO Auto-generated method stub
		return orderDao.saveOrders(order);
	}

}
