package com.example.tradedemo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.tradedemo.domain.CompanyInfo;
import com.example.tradedemo.domain.TransactionInfo;
import com.example.tradedemo.domain.UserPortfolioObj;
import com.example.tradedemo.domain.UserTrnxReq;
import com.example.tradedemo.service.inf.UserInf;

@RestController
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	UserInf userService;

	// To get company share value log
	@GetMapping(value = "/portfolio/{userId}")
	public UserPortfolioObj getUserPortfolio(@PathVariable("userId") final String user) {

		logger.info("Inside User controller");
		return this.userService.getUserPortfolio(user);
	}

	// To get company share value log
	@PostMapping(value = "/Transactions")
	public List<TransactionInfo> getUserTrnxBasedOnRole(@RequestBody UserTrnxReq userReq ) {

		logger.info("Inside User controller");
		return this.userService.getUserTrnx(userReq);
	}

}
