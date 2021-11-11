package com.revobank.dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DebitDTO {

	private Double amount;
	private Instant createdAt;		
	private Long accountId;		

	public DebitDTO(Double amount, Long accountId) {
		this.amount = amount;
		this.createdAt = Instant.now();
		this.accountId = accountId;
	}	

}
