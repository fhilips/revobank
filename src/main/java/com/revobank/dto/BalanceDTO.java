package com.revobank.dto;

import java.time.Instant;

public class BalanceDTO {

	public Double balance;
	public Instant updatedAt;	
	public Long accountId;
		
	public BalanceDTO() {
	
	}

	public BalanceDTO( Double balance, Instant updatedAt, Long accountId) {

		this.balance = balance;
		this.updatedAt = updatedAt;
		this.accountId = accountId;
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
