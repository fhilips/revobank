package com.revobank.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "t_debit")
public class Debit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	public Long id;
	public Double amount;
	public LocalDate createdAt;	
	public Account account;
		
}
