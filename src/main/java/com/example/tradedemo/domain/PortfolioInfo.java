package com.example.tradedemo.domain;

public class PortfolioInfo {
	
	private String symbol;
	private Long shares;
	private Long shareValue;
	private Long TotalValue;
	
	
	
	
	public PortfolioInfo() {
		super();
	}
	
	
	
	public PortfolioInfo(String symbol, Long shares, Long shareValue, Long totalValue) {
		super();
		this.symbol = symbol;
		this.shares = shares;
		this.shareValue = shareValue;
		TotalValue = totalValue;
	}



	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public Long getShares() {
		return shares;
	}
	public void setShares(Long shares) {
		this.shares = shares;
	}
	public Long getShareValue() {
		return shareValue;
	}
	public void setShareValue(Long shareValue) {
		this.shareValue = shareValue;
	}
	public Long getTotalValue() {
		return TotalValue;
	}
	public void setTotalValue(Long totalValue) {
		TotalValue = totalValue;
	}
	
	
	

}
