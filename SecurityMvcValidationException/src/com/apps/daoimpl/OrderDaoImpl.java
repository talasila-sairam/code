package com.apps.daoimpl;

import java.util.Date;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.apps.beans.UserOrders;
import com.apps.dao.OrderDao;
@Repository("orderDao")
public class OrderDaoImpl implements OrderDao{
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public List<Object[]> fetchCart(String userName) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Transaction trans=session.beginTransaction();
		SQLQuery qry = session.createSQLQuery("select p.name,p.code,op.amount,op.ORDER_DATE,op.id,op.Quantity from PRODUCTS p,ORDERS op where p.CODE=op.ORDER_NUM and op.USERNAME='"+userName+"'");
		List<Object[]> data=qry.list();
		return data;
	}
	@Override
	public boolean deleteCartItem(String cartId) {
		// TODO Auto-generated method stub
		try{
			Session session=sessionFactory.openSession();
			Transaction trans=session.beginTransaction();
			UserOrders orders=new UserOrders();
			orders.setId(Integer.parseInt(cartId));
			session.delete(orders);
			trans.commit();
		}catch(Exception e){
			return false;
		}
		return true;
	}
	@Override
	public boolean saveOrders(UserOrders order) {
		// TODO Auto-generated method stub
		UserOrders userOrders=new UserOrders();
		Session session1=sessionFactory.getCurrentSession();
		Transaction transaction1=session1.beginTransaction();
		order.setOrderDate(new Date());
		session1.save(order);
		transaction1.commit();
		return true;
		
	}

}
