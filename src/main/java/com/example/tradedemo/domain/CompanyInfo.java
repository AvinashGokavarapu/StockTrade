package com.example.tradedemo.domain;

import java.time.LocalDateTime;
import java.util.List;

public class CompanyInfo {
	
	
	private String symbol;
	private String companyName;
	private String headQuaters;
	private LocalDateTime listedDate;
	private Long initialStockPrice;
	
	private List<CompanyShareLogInfo> priceLogList;
	
	
	public CompanyInfo() {
		super();
	}



	





	public CompanyInfo(String symbol, String companyName, String headQuaters, LocalDateTime listedDate,
			Long initialStockPrice, List<CompanyShareLogInfo> priceLogList) {
		super();
		this.symbol = symbol;
		this.companyName = companyName;
		this.headQuaters = headQuaters;
		this.listedDate = listedDate;
		this.initialStockPrice = initialStockPrice;
		this.priceLogList = priceLogList;
	}









	public Long getInitialStockPrice() {
		return initialStockPrice;
	}





	public void setInitialStockPrice(Long initialStockPrice) {
		this.initialStockPrice = initialStockPrice;
	}





	public String getSymbol() {
		return symbol;
	}


	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}


	public String getCompanyName() {
		return companyName;
	}


	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	public String getHeadQuaters() {
		return headQuaters;
	}


	public void setHeadQuaters(String headQuaters) {
		this.headQuaters = headQuaters;
	}


	public LocalDateTime getListedDate() {
		return listedDate;
	}


	public void setListedDate(LocalDateTime listedDate) {
		this.listedDate = listedDate;
	}



	public List<CompanyShareLogInfo> getPriceLogList() {
		return priceLogList;
	}




	public void setPriceLogList(List<CompanyShareLogInfo> priceLogList) {
		this.priceLogList = priceLogList;
	}



	
	
	
	

}
