package com.example.tradedemo.orm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "company_stock_value_vw")
public class CompanyCurrentStockView {
	
	@Id
	@Column(name="symbol") 
	private String symbol;
	
	@Column(name="value") 
	private Long currentPrice;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Long getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(Long currentPrice) {
		this.currentPrice = currentPrice;
	}

	@Override
	public String toString() {
		return "CompanyCurrentStockView [symbol=" + symbol + ", currentPrice=" + currentPrice + "]";
	}
	
	

}
