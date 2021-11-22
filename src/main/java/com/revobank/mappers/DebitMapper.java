package com.revobank.mappers;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import com.revobank.dto.DebitDTO;
import com.revobank.model.Balance;
import com.revobank.model.Debit;


public class DebitMapper {

	public static DebitDTO toDto(Debit entity) {
		return new DebitDTO(
				entity.getAmount(),
				entity.getCreatedAt(),
				entity.getId());		
	}
	
	public static Debit toEntity(DebitDTO dto, Balance balanceEntity) {
		return new Debit(null,
				dto.getAmount(),
				Instant.now(),
				balanceEntity);	
	}
	
	public static Debit toEntity(DebitDTO dto) {
		return new Debit(dto.getAccountId(),
				dto.getAmount(),
				Instant.now(),
				new Balance());	
	}
	
	public static List<DebitDTO> toListDto(List<Debit> list) {
		return list.stream().map(x -> DebitMapper.toDto(x)).collect(Collectors.toList());	
	}
	
}
