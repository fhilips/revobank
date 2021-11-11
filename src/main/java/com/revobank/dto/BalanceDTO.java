package com.revobank.dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BalanceDTO {

	public Double balance;
	public Instant updatedAt;	
	public Long accountId;

}
