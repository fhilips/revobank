package com.revobank.dto;

import java.time.LocalDate;

import com.revobank.model.Account;

public class DebitDTO {

	public Double amount;
	public LocalDate createdAt;	
	public Account account;		

	public DebitDTO() {		
	}

	public DebitDTO(Double amount, LocalDate createdAt, Account account) {
		this.amount = amount;
		this.createdAt = createdAt;
		this.account = account;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
		
}
