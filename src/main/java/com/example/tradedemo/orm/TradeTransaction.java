package com.example.tradedemo.orm;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="TRADE_TRANSACTION")
@Entity
public class TradeTransaction implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="trnx_id")
	private Long trnxId;
	
	@Column(name="shares")
	private Long sharesTransferred;
	
	@Column(name="buy_order_id")
	private Long buyOrderId;
	
	@Column(name="sell_order_id")
	private Long sellOrderId;
	
	@Column(name="buyer_user_id")
	private String buyer;
	
	@Column(name="seller_user_id")
	private String seller;
	
	@Column(name="symbol")
	private String symbol;
	
	@Column(name="trnx_share_price")
	private Long trnxSharePrice; // price per share at which trnx happened;
	
	@Column(name="create_date")
	private LocalDateTime create_date;

	public Long getTrnxId() {
		return trnxId;
	}

	public void setTrnxId(Long trnxId) {
		this.trnxId = trnxId;
	}

	public Long getSharesTransferred() {
		return sharesTransferred;
	}

	public void setSharesTransferred(Long sharesTransferred) {
		this.sharesTransferred = sharesTransferred;
	}

	public Long getBuyOrderId() {
		return buyOrderId;
	}

	public void setBuyOrderId(Long buyOrderId) {
		this.buyOrderId = buyOrderId;
	}

	public Long getSellOrderId() {
		return sellOrderId;
	}

	public void setSellOrderId(Long sellOrderId) {
		this.sellOrderId = sellOrderId;
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

	public Long getTrnxSharePrice() {
		return trnxSharePrice;
	}

	public void setTrnxSharePrice(Long trnxSharePrice) {
		this.trnxSharePrice = trnxSharePrice;
	}

	public LocalDateTime getCreate_date() {
		return create_date;
	}

	public void setCreate_date(LocalDateTime create_date) {
		this.create_date = create_date;
	}
	
	
	
	

}
