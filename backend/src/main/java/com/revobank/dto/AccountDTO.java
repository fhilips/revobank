package com.revobank.dto;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

import com.revobank.model.enums.JobTitle;
import com.revobank.model.enums.Status;


public class AccountDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;	
	private String name;	
	private String document;	
	private String birthDate;	
	private JobTitle jobTitle;	
	private Status status;	
	private Instant createdAt;	
	private Instant updatedAt;
	private String account;
	private String accountDigit;
	
	public AccountDTO() {		
	}

	public AccountDTO(Long id, String name, String document, String birthDate, JobTitle jobTitle, Status status,
			Instant createdAt, Instant updatedAt) {
		this.id = id;
		this.name = name;
		this.document = document;
		this.birthDate = birthDate;
		this.jobTitle = jobTitle;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
	
	public AccountDTO(Long id, String name, String document, String birthDate, JobTitle jobTitle, Status status,
			Instant createdAt, Instant updatedAt, String account, String accountDigit) {
		super();
		this.id = id;
		this.name = name;
		this.document = document;
		this.birthDate = birthDate;
		this.jobTitle = jobTitle;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.account = account;
		this.accountDigit = accountDigit;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String account() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDocument() {
		return document;
	}

	public String getAccountDigit() {
		return accountDigit;
	}

	public void setAccountDigit(String accountDigit) {
		this.accountDigit = accountDigit;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}	

}
