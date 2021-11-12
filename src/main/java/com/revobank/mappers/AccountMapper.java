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
	
	public static Account toUpdatedEntity(AccountUpdateDTO dto, Account entity) {
		Account oldEntity = entity;	
		
//		String name = (dto.getName() != null) ? dto.getName() : oldEntity.getName();
		LocalDate birthDate = (dto.getBirthDate() != null) ? stringToLocalDate(dto.getBirthDate()) : oldEntity.getBirthDate();
//		JobTitle jobTitle = (dto.getJobTitle() != null) ? dto.getJobTitle() : oldEntity.getJobTitle();
//		Status status = (dto.getStatus() != null) ? dto.getStatus() : oldEntity.getStatus();
//		
//		entity.setId(oldEntity.getId());
//		entity.setDocument(oldEntity.getDocument());
//		entity.setAccount(oldEntity.getAccount());
//		entity.setAccountDigit(oldEntity.getAccountDigit());
//		entity.setCreatedAt(oldEntity.getCreatedAt());
//		entity.setUpdatedAt(Instant.now());
//		entity.setName(name);
//		entity.setBirthDate(birthDate);
//		entity.setStatus(status);	
//		entity.setJobTitle(jobTitle);
						
//		BeanUtils.copyProperties(dto, entity,
//				"id", "document", "account", "accountDigit", "updatedAt", "createdAt");
//	
	
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
	   
//	    return Stream.of(wrappedDto.getPropertyDescriptors())
//	            .map(FeatureDescriptor -> FeatureDescriptor.getName())
//	            .filter(propertyName -> 
//	            			wrappedDto.getPropertyValue(propertyName) == null |
//	            			wrappedDto.getPropertyValue(propertyName).equals(""))
//	            .toArray(size -> new String[size]);	    
	    return Stream.of(wrappedDto.getPropertyDescriptors())
			        .map(FeatureDescriptor::getName)
			        .filter(propertyName -> 
			        			wrappedDto.getPropertyValue(propertyName) == null ||
			        			wrappedDto.getPropertyValue(propertyName).equals(""))
			        .toArray(String[]::new);
	}
	
//	public static String[] getNullPropertyNames (AccountUpdateDTO dto) {
//	    final BeanWrapper src = new BeanWrapperImpl(dto);
//	    java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();
//
//	    Set<String> emptyNames = new HashSet<String>();
//	    for(java.beans.PropertyDescriptor pd : pds) {
//	        Object srcValue = src.getPropertyValue(pd.getName());
//	        if (srcValue == null) emptyNames.add(pd.getName());
//	    }
//
//	    String[] result = new String[emptyNames.size()];
//	    return emptyNames.toArray(result);
//	}
	
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
