package com.example.tradedemo.orm;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="ORDER_INFO")
@Entity
public class Order implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="order_id")
	private Long orderId;
	
	@Column(name="shares")
	private Long shares;
	
	@Column(name="order_type")
	private String orderType; // B or S
	
	@Column(name="symbol")
	private String symbol;
	
	@Column(name="quote_price")
	private Long quotePrice;
	
	@Column(name="user_id")
	private String userId;
	
	@Column(name="order_status")
	private String orderStatus; // Q or I or C
	
	@Column(name="adm_flag")
	private String admFlag; // V or D
	
	@Column(name="create_date")
	private LocalDateTime createDate;
	
	@Column(name="update_date")
	private LocalDateTime updateDate;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getShares() {
		return shares;
	}

	public void setShares(Long shares) {
		this.shares = shares;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getAdmFlag() {
		return admFlag;
	}

	public void setAdmFlag(String admFlag) {
		this.admFlag = admFlag;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", shares=" + shares + ", orderType=" + orderType + ", symbol=" + symbol
				+ ", quotePrice=" + quotePrice + ", userId=" + userId + ", orderStatus=" + orderStatus + ", admFlag="
				+ admFlag + ", createDate=" + createDate + ", updateDate=" + updateDate + "]";
	}

	
	
	
	
	

}
