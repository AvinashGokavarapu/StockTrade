package com.example.tradedemo.domain;

import java.time.LocalDateTime;

public class CompanyShareLogInfo {
	
	private Long id;
	private LocalDateTime timestamp;
	private Long shareValue;
	
	
	public CompanyShareLogInfo() {
		super();
	}


	public CompanyShareLogInfo(Long id, LocalDateTime timestamp, Long shareValue) {
		super();
		this.id = id;
		this.timestamp = timestamp;
		this.shareValue = shareValue;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public LocalDateTime getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}


	public Long getShareValue() {
		return shareValue;
	}


	public void setShareValue(Long shareValue) {
		this.shareValue = shareValue;
	}
	
	

}
