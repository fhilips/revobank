package com.revobank.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_balance")
public class Balance implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id	
	public Long id;
	@NotNull(message = "The 'Balance' field is required.")
	public Double balance;
	public Instant updatedAt;
	@NotNull(message = "The 'Account ID' field is required.")
	@OneToOne(fetch = FetchType.LAZY)
    @MapsId
	public Account account;
		
	@OneToMany(mappedBy = "balance")
	public List<Debit> debits = new ArrayList<>();
	
	public Balance() {
		
	}

	public Balance(Long id, Double balance, Instant updatedAt, Account account, List<Debit> debits) {
		this.id = id;
		this.balance = balance;
		this.updatedAt = updatedAt;
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

	public Instant getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Instant updatedAt) {
		this.updatedAt = updatedAt;
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
	
}
