package com.revobank.services.test;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.revobank.dto.AccountDTO;
import com.revobank.dto.response.MessageResponseDTO;
import com.revobank.factory.AccountFactory;
import com.revobank.model.Account;
import com.revobank.repositories.AccountRepository;
import com.revobank.services.AccountService;
import com.revobank.services.BalanceService;

@ExtendWith(SpringExtension.class)
public class AccountServiceTests {

	@InjectMocks
	private AccountService service;
	
	@Mock
	private BalanceService balanceService;
	
	@Mock
	private AccountRepository repository;
	
	private Account account;	
	private AccountDTO accountDto;
	private Long nonExistingId;
	private Long existingId;

	@BeforeEach
	void setUp() throws Exception{
		existingId = 1l;
		nonExistingId = 300l;	
		
		account = AccountFactory.createAccount();
		accountDto = AccountFactory.createAccountDTO();
		
		Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(account));
		Mockito.when(repository.findById(nonExistingId)).thenReturn(Optional.empty());		
		Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(account);
	}
	
	@Test
	public void findByIdShouldReturnAccountDTOWhenIdExists() {
		
		AccountDTO acountDto = service.findById(existingId);
		
		Assertions.assertNotNull(acountDto);
	}
	
	@Test
	public void findByIdShouldThrowEntityNotFoundExceptionWhenIdDoesNotExists() {
						
		Assertions.assertThrows(EntityNotFoundException.class, () -> {
			service.findById(nonExistingId);
		});		
	}
	
	@Test
	public void createdAccountShouldReturnCorrectMessageWhenValidData() {		
		MessageResponseDTO dto = service.createAccount(accountDto);
		
		Assertions.assertEquals(dto.getMessage(), "Created account with ID " + existingId);	
	}
	
	@Test
	public void createdAccountShouldReturnMessaResponseWhenValidData() {
		
		MessageResponseDTO dto = service.createAccount(accountDto);
		
		Assertions.assertNotNull(dto);		
	}
		
}