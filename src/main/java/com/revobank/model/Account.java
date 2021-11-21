package com.revobank.model;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.revobank.model.enums.JobTitle;
import com.revobank.model.enums.Status;
import com.revobank.utils.DigitsGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_account")
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;	
	
	@Column(unique = true)    
	private String document;
	
	private LocalDate birthDate;
	
	@Column(nullable = false)		
	private String account;	
	
	@Column(nullable = false)	
	private String accountDigit;
	
	@Enumerated(EnumType.STRING)
	private JobTitle jobTitle;
	
	@Enumerated(EnumType.STRING)
	private Status status;		
	private Instant createdAt;
	private Instant updatedAt;

	@OneToOne(mappedBy = "account", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
	private Balance balance;
		
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
	
}
