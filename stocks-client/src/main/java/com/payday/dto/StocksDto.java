package com.payday.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

//TODO should be placed in common module
public class StocksDto {

	private String code;
	private String name;
	private BigDecimal price;
	private LocalDateTime priceTime;

	
	public StocksDto() {
		super();
	}

	public StocksDto(String code, String name, BigDecimal price, LocalDateTime priceTime) {
		super();
		this.code = code;
		this.name = name;
		this.price = price;
		this.priceTime = priceTime;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public LocalDateTime getPriceTime() {
		return priceTime;
	}

	public void setPriceTime(LocalDateTime priceTime) {
		this.priceTime = priceTime;
	}
}
