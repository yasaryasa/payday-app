package com.payday.dto;

import java.time.LocalDateTime;

import com.payday.utils.TransactionStatusEnum;

public class TransactionDto extends BaseTransactionDto{

	private String id;
	private String userId;
	private String hookUrl;
	private TransactionStatusEnum status;
	private LocalDateTime createDate;
	
	public TransactionDto() {
		super();
	}

	public TransactionDto(BaseTransactionDto base) {
		super();
		this.setPrice(base.getPrice());
		this.setAmount(base.getAmount());
		this.setStockCode(base.getStockCode());
		this.setStockName(base.getStockName());
		this.setTransactionType(base.getTransactionType());
	}

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


	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

}
