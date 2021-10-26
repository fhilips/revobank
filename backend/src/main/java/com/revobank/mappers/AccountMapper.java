package com.revobank.mappers;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import com.revobank.dto.AccountDTO;
import com.revobank.model.Account;
import com.revobank.model.enums.JobTitle;
import com.revobank.model.enums.Status;

public class AccountMapper {
	
	public static AccountDTO toDto(Account entity) {		
		return new AccountDTO(entity.getId(),
				entity.getName(),
				entity.getDocument(),
				entity.getBirthDate().toString(),
				entity.getJobTitle(),
				entity.getStatus(),
				entity.getCreatedAt(),										
			    entity.getUpdatedAt());			
	}
	
	public static AccountDTO toDtoOnResponse(Account entity) {
		return new AccountDTO(entity.getId(),
				entity.getName(),
				entity.getDocument(),
				entity.getBirthDate().toString(),
				entity.getJobTitle(),
				entity.getStatus(),
				entity.getCreatedAt(),										
			    entity.getUpdatedAt(),
			    entity.getAccount(),
			    entity.getAccountDigit());		
	}
	
	public static Account toEntity(AccountDTO dto) {		
		return new Account(dto.getId(),
				dto.getName(),
				dto.getDocument(),
				stringToLocalDate(dto.getBirthDate()),				
				dto.getJobTitle(),
				dto.getStatus(),
				Instant.now(),
				dto.getUpdatedAt()				
				);
	}
	
	public static Account toEntityOnCreate(AccountDTO dto) {		
		return new Account(dto.getId(),
				dto.getName(),
				dto.getDocument(),
				stringToLocalDate(dto.getBirthDate()),				
				dto.getJobTitle(),
				dto.getStatus(),
				Instant.now(),
				Instant.now()				
				);
	}
	
	public static Account toUpdatedEntity(AccountDTO dto, Account entity) {
		Account oldEntity = entity;	
		
		String name = (dto.getName() != null) ? dto.getName() : oldEntity.getName();
		LocalDate birthDate = (dto.getBirthDate() != null) ? stringToLocalDate(dto.getBirthDate()) : oldEntity.getBirthDate();
		JobTitle jobTitle = (dto.getJobTitle() != null) ? dto.getJobTitle() : oldEntity.getJobTitle();
		Status status = (dto.getStatus() != null) ? dto.getStatus() : oldEntity.getStatus();
		
		entity.setId(oldEntity.getId());
		entity.setDocument(oldEntity.getDocument());
		entity.setAccount(oldEntity.getAccount());
		entity.setAccountDigit(oldEntity.getAccountDigit());
		entity.setCreatedAt(oldEntity.getCreatedAt());
		entity.setUpdatedAt(Instant.now());
		entity.setName(name);
		entity.setBirthDate(birthDate);
		entity.setStatus(status);	
		entity.setJobTitle(jobTitle);
					
		return entity;
	}
	
	public static List<AccountDTO> toListDto(List<Account> entityList) {
		return entityList.stream()
				.map(x -> AccountMapper.toDto(x)).collect(Collectors.toList());
	}

	public static List<AccountDTO> toListDtoOnResponse(List<Account> entityList) {		
		return entityList.stream()
				.map(x -> AccountMapper.toDtoOnResponse(x)).collect(Collectors.toList());
	}		
		
	private static LocalDate stringToLocalDate(String date) {		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");		
		return LocalDate.parse(date, formatter);
	}
}
