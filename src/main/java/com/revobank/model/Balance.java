package com.revobank.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
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

	public Balance(Double balance, Instant updatedAt, Account account) {
		this.balance = balance;
		this.updatedAt = updatedAt;
		this.account = account;		
	}

}
