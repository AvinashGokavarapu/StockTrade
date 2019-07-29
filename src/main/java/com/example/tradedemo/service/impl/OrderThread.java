package com.example.tradedemo.service.impl;

import com.example.tradedemo.dao.inf.OrderDaoInf;
import com.example.tradedemo.orm.Order;

public class OrderThread implements Runnable {
	
	private Order order;
	private OrderDaoInf orderService;
	

	public OrderThread(Order order, OrderDaoInf orderService) {
		super();
		this.order = order;
		this.orderService = orderService;
	}




	@Override
	public void run() {
		
		try {
			
			Thread.sleep(10000);
			
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
		System.out.println("starting process now");
		System.out.println("order id is " + order.getOrderId());
		this.orderService.processOrder(order);

	}

}
