package com.revobank.dto;

import java.time.Instant;

public class DebitDTO {

	private Double amount;
	private Instant createdAt;		
	private Long accountId;		

	public DebitDTO() {		
	}

	public DebitDTO(Double amount, Long accountId) {
		this.amount = amount;
		this.createdAt = Instant.now();
		this.accountId = accountId;
	}	

	public DebitDTO(Double amount, Instant createdAt, Long accountId) {		
		this.amount = amount;
		this.createdAt = createdAt;
		this.accountId = accountId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

}
