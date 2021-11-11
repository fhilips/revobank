package com.revobank.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revobank.dto.AccountDTO;
import com.revobank.dto.AccountUpdateDTO;
import com.revobank.dto.response.MessageResponseDTO;
import com.revobank.mappers.AccountMapper;
import com.revobank.model.Account;
import com.revobank.model.enums.Status;
import com.revobank.repositories.AccountRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AccountService{
	
	private AccountRepository accountRepository;
	
	private BalanceService balanceService;
	
	@Transactional
	public MessageResponseDTO createAccount(AccountDTO accountDTO) {			
		Account entity = AccountMapper.toEntityOnCreate(accountDTO);		
		Account savedAccount = accountRepository.save(entity);
		balanceService.createBalance(savedAccount);		
		return createMessageResponse("Created account with ID ", savedAccount.getId());
	}
	
	@Transactional
	public MessageResponseDTO updateByDto(Long id, AccountUpdateDTO dto) {
		Account entity = verifyIfExists(id);		
		Account updatedEntity = AccountMapper.toUpdatedEntity(dto, entity);				
		Account savedEntity = accountRepository.save(updatedEntity);		
		return createMessageResponse("Updated account with ID ", savedEntity.getId());	
	}
		
	@Transactional(readOnly = true)
	public List<AccountDTO> getAllAccounts() {
		List<Account> entities = accountRepository.findAll();		
		return AccountMapper.toListDtoOnResponse(entities);		
	}	
				
	@Transactional(readOnly = true)
	public AccountDTO findById(Long id) {
		Account entity = verifyIfExists(id);
		return AccountMapper.toDtoOnResponse(entity);
	}	

	@Transactional
	public void blockAccount(Long accountId) {
		Account account = verifyIfExists(accountId);		
		account.setStatus(Status.BLOCKED);
		accountRepository.save(account);		
	}
	
	@Transactional(readOnly = true)
	private Account verifyIfExists(Long id) throws RuntimeException {		
		return accountRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Account not found."));
	}
	
	private MessageResponseDTO createMessageResponse(String message, Long id) {
        return new MessageResponseDTO(message + id);                
    }	
		
}
