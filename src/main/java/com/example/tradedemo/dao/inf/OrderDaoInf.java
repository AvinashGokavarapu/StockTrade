package com.example.tradedemo.dao.inf;

import com.example.tradedemo.domain.OrderInfo;
import com.example.tradedemo.orm.Order;

public interface OrderDaoInf {

	Order placeOrder(OrderInfo order);
	
	void processOrder(Order placedOrder);

}
