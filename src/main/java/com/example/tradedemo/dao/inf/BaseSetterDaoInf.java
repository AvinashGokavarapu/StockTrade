package com.example.tradedemo.dao.inf;

import java.util.List;

import com.example.tradedemo.domain.CompanyInfo;
import com.example.tradedemo.domain.CompanySharesDistribution;
import com.example.tradedemo.orm.CompanyCurrentStockView;
import com.example.tradedemo.orm.TradeCompany;

public interface BaseSetterDaoInf {
	
	public String listCompany(CompanyInfo compInfo);

	public TradeCompany getCompanyLog(String symbol);

	public String distributeShares(CompanySharesDistribution shareDist);

	public  List<CompanyCurrentStockView> getCurrentShareValue(List<String> compList);

}
