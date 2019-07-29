package com.example.tradedemo.orm;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Table(name="TRADE_COMPANY")
@Entity
public class TradeCompany implements java.io.Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="symbol")
	private String symbol;
	
	@Column(name="company_name")
	private String companyName;
	
	@Column(name="head_quaters")
	private String headQuaters;
	
	@Column(name="listed_date")
	private LocalDateTime listedDate;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "symbol")
	@Cascade(CascadeType.SAVE_UPDATE)
	@OrderBy("timeStamp DESC")
	private List<CompanyShareLog> shareValueLog ;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	
	

	public List<CompanyShareLog> getShareValueLog() {
		return shareValueLog;
	}

	public void setShareValueLog(List<CompanyShareLog> shareValueLog) {
		this.shareValueLog = shareValueLog;
	}

	@Override
	public String toString() {
		return "TradeCompany [id=" + id + ", symbol=" + symbol + ", companyName=" + companyName + ", headQuaters="
				+ headQuaters + ", listedDate=" + listedDate + ", shareValueLog=" + shareValueLog + "]";
	}

	
	
	
	
	

}
