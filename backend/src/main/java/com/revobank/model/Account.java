package com.revobank.model;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.revobank.model.enums.JobTitle;
import com.revobank.model.enums.Status;
import com.revobank.resources.utils.DigitsGenerator;

@Entity
@Table(name = "tb_account")
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull(message = "The 'name' field is required.")
	private String name;	
	@NotNull(message = "The 'Document' field is required.")
	@Column(unique = true)
    @CPF
	private String document;
	@NotNull(message = "The 'Birth Date' field is required.")
	private LocalDate birthDate;	
	@Column(nullable = false)
	private String account;	
	@Column(nullable = false)	
	private String accountDigit;
	@NotNull(message = "The 'Job Title' field is required.")
	@Enumerated(EnumType.STRING)
	private JobTitle jobTitle;
	@NotNull(message = "The 'Status' field is required.")
	@Enumerated(EnumType.STRING)
	private Status status;		
	private Instant createdAt;
	private Instant updatedAt;
	
	@OneToOne(mappedBy = "account")
	private Balance balance;

	public Account() {
	}
		
	public Account(Long id, String name, String document, LocalDate birthDate, JobTitle jobTitle,
			Status status, Instant createdAt, Instant updatedAt) {
		this.id = id;
		this.name = name;
		this.document = document;
		this.birthDate = birthDate;
		this.jobTitle = jobTitle;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;		
		this.account = DigitsGenerator.generateDigits(7);
		this.accountDigit = DigitsGenerator.generateDigits(1);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAccountDigit() {
		return accountDigit;
	}

	public void setAccountDigit(String accountDigit) {
		this.accountDigit = accountDigit;
	}

	public JobTitle getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(JobTitle jobTitle) {
		this.jobTitle = jobTitle;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	public Instant getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Instant updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Balance getBalance() {
		return balance;
	}

	public void setBalance(Balance balance) {
		this.balance = balance;
	}
	
}
