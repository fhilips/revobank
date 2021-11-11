package com.revobank.dto;

import java.io.Serializable;
import java.time.Instant;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.revobank.model.enums.JobTitle;
import com.revobank.model.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

}
