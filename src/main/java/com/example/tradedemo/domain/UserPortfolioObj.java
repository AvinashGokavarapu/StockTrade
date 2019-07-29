package com.example.tradedemo.domain;

import java.util.List;

public class UserPortfolioObj {
	
	private String name ;
	private String contact;
	private String mail;
	private Long amount;
	private List<PortfolioInfo> portfolioList;
	
	
	public UserPortfolioObj() {
		super();
	}


	public UserPortfolioObj(String name, String contact, String mail, Long amount, List<PortfolioInfo> portfolioList) {
		super();
		this.name = name;
		this.contact = contact;
		this.mail = mail;
		this.amount = amount;
		this.portfolioList = portfolioList;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getContact() {
		return contact;
	}


	public void setContact(String contact) {
		this.contact = contact;
	}


	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}


	public Long getAmount() {
		return amount;
	}


	public void setAmount(Long amount) {
		this.amount = amount;
	}


	public List<PortfolioInfo> getPortfolioList() {
		return portfolioList;
	}


	public void setPortfolioList(List<PortfolioInfo> portfolioList) {
		this.portfolioList = portfolioList;
	}
	
	
	

}
