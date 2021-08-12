package com.payday.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.payday.utils.TransactionStatusEnum;
import com.payday.utils.TransactionTypeEnum;

@Entity
public class Transaction {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;// transaction_id
	private String userId;
	// FIXME stockId yerine stock code unique'liği sağlayabilir
	// bu sebeple tasarımdan kaldırılabilir
//	private Integer stockId;
	private String stockCode;
	private String stockName;
	private BigDecimal price;
	private Integer amount;
	private String hookUrl;
	@Enumerated(EnumType.STRING)
	private TransactionStatusEnum status = TransactionStatusEnum.REQUESTED;
	private LocalDateTime createDate; 
	@Enumerated(EnumType.STRING)
	private TransactionTypeEnum transactionType;


	public String getHookUrl() {
		return hookUrl;
	}

	public void setHookUrl(String hookUrl) {
		this.hookUrl = hookUrl;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TransactionStatusEnum getStatus() {
		return status;
	}

	public void setStatus(TransactionStatusEnum status) {
		this.status = status;
	}

	public TransactionTypeEnum getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionTypeEnum transactionType) {
		this.transactionType = transactionType;
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

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
}
