package com.revobank.mappers;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import com.revobank.dto.BalanceDTO;
import com.revobank.model.Balance;

public class BalanceMapper {
	
	public static BalanceDTO toDto(Balance entity) {
		return new BalanceDTO(entity.getId(),
							entity.getBalance(),
							Instant.now(),
							entity.getId());
	}
	
	public static List<BalanceDTO> toListDto(List<Balance> balances) {
		return balances.stream()
					.map(x -> BalanceMapper.toDto(x)).collect(Collectors.toList());
				
	}
	
}
