package com.payday.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.payday.utils.TransactionStatusEnum;
import com.payday.utils.TransactionTypeEnum;

public class TransactionDto {

	private String id;
	private String userId;
	private String stockCode;
	private String stockName;
	private BigDecimal price;
	private Integer amount;
	private String hookUrl;
	private TransactionStatusEnum status;
	private LocalDateTime createDate;
	private TransactionTypeEnum transactionType;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHookUrl() {
		return hookUrl;
	}

	public void setHookUrl(String hookUrl) {
		this.hookUrl = hookUrl;
	}

	public TransactionTypeEnum getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionTypeEnum transactionType) {
		this.transactionType = transactionType;
	}

	public TransactionStatusEnum getStatus() {
		return status;
	}

	public void setStatus(TransactionStatusEnum status) {
		this.status = status;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

}
