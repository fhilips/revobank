package com.revobank.dto;

import java.io.Serializable;
import java.time.Instant;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.revobank.model.enums.JobTitle;
import com.revobank.model.enums.Status;

public class AccountDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	@NotBlank(message = "The 'name' field is required.")
	private String name;
	@NotBlank(message = "The 'Document' field is required.")
	@CPF
	private String document;
	@NotNull(message = "The 'Birth Date' field is required.")
	private String birthDate;
	@NotNull(message = "The 'Job Title' field is required.")
	private JobTitle jobTitle;
	@NotNull(message = "The 'Status' field is required.")
	private Status status;
	private Instant createdAt;
	private Instant updatedAt;
	private String account;
	private String accountDigit;

	public AccountDTO() {
	}

	public String getName() {
		return name;
	}

	public AccountDTO(Long id, @NotBlank(message = "The 'name' field is required.") String name,
			@NotBlank(message = "The 'Document' field is required.") @CPF String document,
			@NotNull(message = "The 'Birth Date' field is required.") String birthDate,
			@NotNull(message = "The 'Job Title' field is required.") JobTitle jobTitle,
			@NotNull(message = "The 'Status' field is required.") Status status, Instant createdAt, Instant updatedAt) {

		this.id = id;
		this.name = name;
		this.document = document;
		this.birthDate = birthDate;
		this.jobTitle = jobTitle;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public AccountDTO(Long id, @NotBlank(message = "The 'name' field is required.") String name,
			@NotBlank(message = "The 'Document' field is required.") @CPF String document,
			@NotNull(message = "The 'Birth Date' field is required.") String birthDate,
			@NotNull(message = "The 'Job Title' field is required.") JobTitle jobTitle,
			@NotNull(message = "The 'Status' field is required.") Status status, Instant createdAt, Instant updatedAt,
			String account, String accountDigit) {
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
