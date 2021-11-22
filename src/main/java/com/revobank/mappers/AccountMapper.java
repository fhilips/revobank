package com.revobank.mappers;

import java.beans.FeatureDescriptor;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import com.revobank.dto.AccountDTO;
import com.revobank.dto.AccountUpdateDTO;
import com.revobank.model.Account;
import com.revobank.model.enums.Status;

public class AccountMapper {
	
	public static AccountDTO toDto(Account entity) {		
		return new AccountDTO(entity.getId(),
				entity.getName(),
				entity.getDocument(),
				localDateToString(entity.getBirthDate()),
				entity.getJobTitle(),
				entity.getStatus(),
				entity.getCreatedAt(),										
			    entity.getUpdatedAt());			
	}
	
	public static AccountDTO toDtoOnResponse(Account entity) {
		return new AccountDTO(entity.getId(),
				entity.getName(),
				entity.getDocument(),
				localDateToString(entity.getBirthDate()),
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
				Status.ACTIVE,
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
	
	public static Account toUpdatedEntity(AccountUpdateDTO dto, Account entity) {
		Account oldEntity = entity;	
		LocalDate birthDate = (dto.getBirthDate() != null) ? stringToLocalDate(dto.getBirthDate()) : oldEntity.getBirthDate();

		BeanUtils.copyProperties(dto, entity, getInvalidOrUnrequiredParameters(dto));
	
		entity.setUpdatedAt(Instant.now());	
		entity.setBirthDate(birthDate);
		return entity;
	}
	
	private static String[] getInvalidOrUnrequiredParameters(AccountUpdateDTO dto) {
		Set<String> set = new HashSet<>(Arrays.asList(getBlankPropertyNames(dto)));	
		set.addAll(Set.of("id", "document", "account", "accountDigit", "updatedAt", "createdAt"));
		
		return set.stream().toArray(size -> new String[size]);
	}
	
	private static String[] getBlankPropertyNames(AccountUpdateDTO dto) {
	    final BeanWrapper wrappedDto = new BeanWrapperImpl(dto);
    
	    return Stream.of(wrappedDto.getPropertyDescriptors())
			        .map(FeatureDescriptor::getName)
			        .filter(propertyName -> 
			        			wrappedDto.getPropertyValue(propertyName) == null ||
			        			wrappedDto.getPropertyValue(propertyName).equals(""))
			        .toArray(String[]::new);
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
	
	private static String localDateToString(LocalDate localDate) {		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String formattedDate = localDate.format(formatter);
		return formattedDate;
	}
}
