package com.revobank.dto;

import java.io.Serializable;

import javax.persistence.Id;

import com.revobank.model.Balance;


public class AccountDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	public Long id;
	
	public Balance balance;
	

	public AccountDTO() {		
	}

	public AccountDTO(Long id, Balance balance) {
		super();
		this.id = id;
		this.balance = balance;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Balance getBalance() {
		return balance;
	}

	public void setBalance(Balance balance) {
		this.balance = balance;
	}	
	
}
