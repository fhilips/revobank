package com.revobank.model.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revobank.dto.AccountDTO;
import com.revobank.mappers.AccountMapper;
import com.revobank.model.Account;
import com.revobank.model.repositories.AccountRepository;

@Service
public class AccountService{
	
	private AccountRepository accountRepository;
	
	private BalanceService balanceService;
	
	@Autowired
	public AccountService(AccountRepository accountRepository, BalanceService balanceService) {		
		this.accountRepository = accountRepository;
		this.balanceService = balanceService;
	}
	
	public AccountDTO createAccount(AccountDTO accountDTO) {			
		Account entity = AccountMapper.toEntityOnCreate(accountDTO);		
		Account savedAccount = accountRepository.save(entity);
		balanceService.createBalance(savedAccount);		
		return AccountMapper.toDtoOnResponse(savedAccount);
	}
	
	public List<AccountDTO> getAllAccounts() {
		List<Account> entities = accountRepository.findAll();		
		return AccountMapper.toListDtoOnResponse(entities);		
	}	
	
	public void updateByEntity(Account entity) {
		verifyIfExists(entity.getId());		
		accountRepository.save(entity);		
	}
	
	public AccountDTO updateByDto(Long id, AccountDTO dto) {
		Account entity = verifyIfExists(id);		
		Account updatedEntity = AccountMapper.toUpdatedEntity(dto, entity);				
		Account savedEntity = accountRepository.save(updatedEntity);
		
		return AccountMapper.toDtoOnResponse(savedEntity);	
	}
	
	public AccountDTO findById(Long id) {
		Account entity = verifyIfExists(id);
		return AccountMapper.toDtoOnResponse(entity);
	}
	
	private Account verifyIfExists(Long id) throws RuntimeException {		
		return accountRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Account not found."));
	}
		
}
