package com.example.tradedemo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tradedemo.dao.inf.OrderDaoInf;
import com.example.tradedemo.domain.OrderInfo;
import com.example.tradedemo.orm.Order;
import com.example.tradedemo.service.inf.OrderInf;

@Service
public class OrderImpl implements OrderInf{
	
	@Autowired
	OrderDaoInf OrderDaoService;

	@Override
	public String placeOrder(OrderInfo order) {
		
		Order placedOrder = this.OrderDaoService.placeOrder(order);
		
		Thread placedOrderThread = new Thread(new OrderThread(placedOrder,OrderDaoService));
		placedOrderThread.start();
		
		
		return "Order placed succesfully";
		
	}

}
