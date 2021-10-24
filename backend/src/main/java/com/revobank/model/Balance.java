package com.revobank.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "t_balance")
public class Balance implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	public Long id;
	public Double balance;
	public Account account;
	public List<Debit> debits = new ArrayList<>();
	
	public Balance() {
		
	}

	public Balance(Long id, Double balance, Account account, List<Debit> debits) {
		
		this.id = id;
		this.balance = balance;
		this.account = account;
		this.debits = debits;
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

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public List<Debit> getDebits() {
		return debits;
	}

	public void setDebits(List<Debit> debits) {
		this.debits = debits;
	}

}
