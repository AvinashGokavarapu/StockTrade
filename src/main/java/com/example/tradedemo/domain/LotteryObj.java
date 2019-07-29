package com.example.tradedemo.domain;

public class LotteryObj {
	
	private String userId;
	private Long shares;
	
	public LotteryObj() {
		super();
	}

	public LotteryObj(String userId, Long shares) {
		super();
		this.userId = userId;
		this.shares = shares;
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
	
	

}
