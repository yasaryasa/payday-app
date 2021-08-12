package com.payday.dto;

import java.math.BigDecimal;

import com.payday.utils.TransactionTypeEnum;

public class UserStocksTransactionDto {

	private String userId;
	private String stockCode;
	private String stockName;
	private Integer amount;
	private BigDecimal price;
	private TransactionTypeEnum transactionType;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String getStockCode() {
		return stockCode;
	}
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}
	public TransactionTypeEnum getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(TransactionTypeEnum transactionType) {
		this.transactionType = transactionType;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
