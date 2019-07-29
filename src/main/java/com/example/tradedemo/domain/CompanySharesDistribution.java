package com.example.tradedemo.domain;

import java.util.List;
import java.util.Map;

public class CompanySharesDistribution {
	
	private String company;
	private String symbol;
	private List<LotteryObj> lotteryList;
	
	
	
	public CompanySharesDistribution() {
		super();
	}
	
	
	
	
	public CompanySharesDistribution(String company, String symbol, List<LotteryObj> lotteryList) {
		super();
		this.company = company;
		this.symbol = symbol;
		this.lotteryList = lotteryList;
	}




	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public List<LotteryObj> getLotteryList() {
		return lotteryList;
	}
	public void setLotteryList(List<LotteryObj> lotteryList) {
		this.lotteryList = lotteryList;
	}
	
	
	

}
