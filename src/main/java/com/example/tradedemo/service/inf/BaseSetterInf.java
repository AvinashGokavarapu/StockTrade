package com.example.tradedemo.service.inf;

import java.util.List;

import com.example.tradedemo.domain.CompanyInfo;
import com.example.tradedemo.domain.CompanySharesDistribution;
import com.example.tradedemo.orm.CompanyCurrentStockView;

public interface BaseSetterInf {
	
	public String listCompany(CompanyInfo compInfo);

	public CompanyInfo getCompanyLog(String symbol);

	public String distributeShares(CompanySharesDistribution shareDist);

	public  List<CompanyCurrentStockView> getCurrentShareValue(List<String> compList);
	
	

}
