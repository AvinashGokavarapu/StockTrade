package com.example.tradedemo.orm;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name="COMPANY_SHARE_VALUE_LOG")
@Entity
public class CompanyShareLog implements java.io.Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="log_id")
	private Long logId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="symbol", referencedColumnName="symbol",  nullable = false)
	private TradeCompany symbol;
	
	@Column(name="create_date")
	private LocalDateTime timeStamp;
	
	@Column(name="value")
	private Long shareValue;

	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public TradeCompany getSymbol() {
		return symbol;
	}

	public void setSymbol(TradeCompany symbol) {
		this.symbol = symbol;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Long getShareValue() {
		return shareValue;
	}

	public void setShareValue(Long shareValue) {
		this.shareValue = shareValue;
	}

	@Override
	public String toString() {
		return "CompanyShareLog [logId=" + logId + ", symbol=" + symbol + ", timeStamp=" + timeStamp + ", shareValue="
				+ shareValue + "]";
	}
	
	
	

}
