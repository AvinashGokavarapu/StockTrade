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
import com.example.tradedemo.domain.CompanySharesDistribution;
import com.example.tradedemo.orm.CompanyCurrentStockView;
import com.example.tradedemo.service.inf.BaseSetterInf;

@RestController
public class BaseSetterController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	BaseSetterInf baseService;

	// To list a new company
	@PostMapping(value = "/companyListing")
	public String getPrrqReviews(@RequestBody CompanyInfo compInfo) {

		logger.info("Inside base controller");
		return this.baseService.listCompany(compInfo);
	}

	// To get company share value log
	@GetMapping(value = "/companyLog/{symbol}")
	public CompanyInfo getCompanyLog(@PathVariable("symbol") final String symbol) {

		logger.info("Inside base controller");
		return this.baseService.getCompanyLog(symbol);
	}

	// To distribute shares in lottery
	@PostMapping(value = "/distributeShares")
	public String distributeShares(@RequestBody CompanySharesDistribution shareDist) {

		logger.info("Inside base controller");
		return this.baseService.distributeShares(shareDist);
	}
	
	
		// To get company share value log
		@PostMapping(value = "/currentShareValue")
		public  List<CompanyCurrentStockView> getCurrentShareValue(@RequestBody List<String> compList) {

			logger.info("Inside base controller");
			 return this.baseService.getCurrentShareValue(compList);
		}
	

}
