package com.revobank.dto;

import java.time.Instant;

public class BalanceDTO {

	public Long id;		
	public Double balance;
	public Instant updatedAt;	
	public Long accountId;
		
	public BalanceDTO() {
	
	}

	public BalanceDTO(Long id, Double balance, Instant updatedAt, Long accountId) {
		this.id = id;
		this.balance = balance;
		this.updatedAt = updatedAt;
		this.accountId = accountId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Instant getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Instant updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
		
}
