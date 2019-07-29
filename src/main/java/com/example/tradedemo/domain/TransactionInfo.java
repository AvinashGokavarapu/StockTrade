package com.example.tradedemo.domain;

import java.time.LocalDateTime;

public class TransactionInfo {
	
	private Long transactionId;
	private Long shares;
	private String buyer;
	private String seller;
	private String symbol;
	private Long sharePrice;
	private LocalDateTime date;
	
	
	public TransactionInfo() {
		super();
	}


	public TransactionInfo(Long transactionId, Long shares, String buyer, String seller, String symbol, Long sharePrice,
			LocalDateTime date) {
		super();
		this.transactionId = transactionId;
		this.shares = shares;
		this.buyer = buyer;
		this.seller = seller;
		this.symbol = symbol;
		this.sharePrice = sharePrice;
		this.date = date;
	}


	public Long getTransactionId() {
		return transactionId;
	}


	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}


	public Long getShares() {
		return shares;
	}


	public void setShares(Long shares) {
		this.shares = shares;
	}


	public String getBuyer() {
		return buyer;
	}


	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}


	public String getSeller() {
		return seller;
	}


	public void setSeller(String seller) {
		this.seller = seller;
	}


	public String getSymbol() {
		return symbol;
	}


	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}


	public Long getSharePrice() {
		return sharePrice;
	}


	public void setSharePrice(Long sharePrice) {
		this.sharePrice = sharePrice;
	}


	public LocalDateTime getDate() {
		return date;
	}


	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
	

}
