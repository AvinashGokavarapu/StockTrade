package com.example.tradedemo.orm;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="USER_PORTFOLIO")
@Entity
public class UserPortfolio implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="user_id")
	private String userId;
	
	@Column(name="symbol")
	private String symbol;
	
	@Column(name="shares")
	private Long sharesAvailable;
	
	@Column(name="create_date")
	private LocalDateTime createDate;
	
	@Column(name="update_date")
	private LocalDateTime updateDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Long getSharesAvailable() {
		return sharesAvailable;
	}

	public void setSharesAvailable(Long sharesAvailable) {
		this.sharesAvailable = sharesAvailable;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return "UserPortfolio [id=" + id + ", userId=" + userId + ", symbol=" + symbol + ", sharesAvailable="
				+ sharesAvailable + ", createDate=" + createDate + ", updateDate=" + updateDate + "]";
	}
	
	
	
	
	
	
	
	

}
