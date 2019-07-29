package com.example.tradedemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.tradedemo.domain.OrderInfo;
import com.example.tradedemo.service.inf.OrderInf;

@RestController
public class OrderController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Autowired
	OrderInf orderService;
	
	// To list a new company
	@PostMapping(value="/placeOrder")
	public String saveOrder(@RequestBody OrderInfo order) {
		
		logger.info("Inside base controller");
		return this.orderService.placeOrder(order);
	}

}
