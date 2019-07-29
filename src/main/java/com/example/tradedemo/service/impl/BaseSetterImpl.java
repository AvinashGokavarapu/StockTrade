package com.example.tradedemo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tradedemo.dao.inf.BaseSetterDaoInf;
import com.example.tradedemo.domain.CompanyInfo;
import com.example.tradedemo.domain.CompanyShareLogInfo;
import com.example.tradedemo.domain.CompanySharesDistribution;
import com.example.tradedemo.orm.CompanyCurrentStockView;
import com.example.tradedemo.orm.CompanyShareLog;
import com.example.tradedemo.orm.TradeCompany;
import com.example.tradedemo.service.inf.BaseSetterInf;


@Service
public class BaseSetterImpl implements BaseSetterInf {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	BaseSetterDaoInf baseServiceDao;

	@Override
	public String listCompany(CompanyInfo compInfo) {
		
		return this.baseServiceDao.listCompany(compInfo);
		
	}

	@Override
	public CompanyInfo getCompanyLog(String symbol) {
		
		TradeCompany company = this.baseServiceDao.getCompanyLog(symbol);
		
		CompanyInfo compInfo = new CompanyInfo();
		compInfo.setCompanyName(company.getCompanyName());
		compInfo.setHeadQuaters(company.getHeadQuaters());
		compInfo.setSymbol(company.getSymbol());
		compInfo.setListedDate(company.getListedDate());
		
		List<CompanyShareLogInfo> logList = new ArrayList<CompanyShareLogInfo>();
		
		for(CompanyShareLog shareLog : company.getShareValueLog()) {
			
			CompanyShareLogInfo logInfo = new CompanyShareLogInfo();
			logInfo.setShareValue(shareLog.getShareValue());
			logInfo.setTimestamp(shareLog.getTimeStamp());
			logInfo.setId(shareLog.getLogId());
			
			logList.add(logInfo);
			
		}
		
		compInfo.setPriceLogList(logList);
		
		return compInfo;
	}

	@Override
	public String distributeShares(CompanySharesDistribution shareDist) {
		
		return this.baseServiceDao.distributeShares(shareDist);
		
	}

	@Override
	public  List<CompanyCurrentStockView> getCurrentShareValue(List<String> compList) {
		
		return this.baseServiceDao.getCurrentShareValue(compList);
		
	}

}
