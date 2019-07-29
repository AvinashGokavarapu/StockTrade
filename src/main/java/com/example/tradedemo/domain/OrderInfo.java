package com.example.tradedemo.domain;

public class OrderInfo {
	
	private String userId;
	private Long shares;
	private String type;
	private String symbol;
	private Long quotePrice;
	
	public OrderInfo() {
		super();
	}

	public OrderInfo(String userId, Long shares, String type, String symbol, Long quotePrice) {
		super();
		this.userId = userId;
		this.shares = shares;
		this.type = type;
		this.symbol = symbol;
		this.quotePrice = quotePrice;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Long getShares() {
		return shares;
	}

	public void setShares(Long shares) {
		this.shares = shares;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Long getQuotePrice() {
		return quotePrice;
	}

	public void setQuotePrice(Long quotePrice) {
		this.quotePrice = quotePrice;
	}
	
	
	

}
