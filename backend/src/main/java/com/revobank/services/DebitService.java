package com.revobank.services;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revobank.dto.AccountDTO;
import com.revobank.dto.DebitDTO;
import com.revobank.dto.response.MessageResponseDTO;
import com.revobank.exceptions.ForbiddenArgumentException;
import com.revobank.mappers.DebitMapper;
import com.revobank.model.Account;
import com.revobank.model.Balance;
import com.revobank.model.Debit;
import com.revobank.model.enums.JobTitle;
import com.revobank.model.enums.Status;
import com.revobank.repositories.DebitRepository;


@Service
public class DebitService{

	public DebitRepository debitRepository;
	
	public BalanceService balanceService;
	
	public AccountService accountService;
		
	@Autowired
	public DebitService(DebitRepository debitRepository, BalanceService balanceService, AccountService accountService) {
		this.debitRepository = debitRepository;		
		this.balanceService = balanceService;	
		this.accountService = accountService;
	}

	public MessageResponseDTO addDebit(DebitDTO dto) {
		Debit debitEntity = toEntity(dto);
		Balance balanceEntity = balanceService.findEntityByAccountID(dto.getAccountId());
				
		Status accountStatus = balanceEntity.getAccount().getStatus();
		
		balanceService.verifyIfAccountBlocked(accountStatus);
		
		if(dto.getAmount() > balanceEntity.getBalance()) {
			verifyIfAccountShouldBeBlocked(balanceEntity.getAccount());		
			return createMessageResponse("Error to create debit on balance ID ", debitEntity.getBalance().getId());
		} else {			
			balanceService.updateTotalAmount(dto);
			debitRepository.save(debitEntity);
			return createMessageResponse("Debit created on balance ID ", debitEntity.getBalance().getId());
		}							
	}	

	public List<DebitDTO> getAllDebitsByAccountId(Long id) {
		AccountDTO account = accountService.findById(id);
		balanceService.verifyIfAccountBlocked(account.getStatus());
		
		List<Debit> allDebits = debitRepository.findAllByBalanceId(id);
		return DebitMapper.toListDto(allDebits);
	}
		
	private Debit toEntity(DebitDTO dto) {
		Debit entity = new Debit();		
		Balance balance = balanceService.findById(dto.getAccountId());		
		entity.setAmount(dto.getAmount());
		entity.setBalance(balance);
		entity.setCreatedAt(Instant.now());
		return entity;
	}
	
	private void verifyIfAccountShouldBeBlocked(Account account) {
		JobTitle jobTitle = account.getJobTitle();
		if(jobTitle.shouldBlockByInvalidAmount()) {
			account.setStatus(Status.BLOCKED);
			accountService.updateByEntity(account);
			throw new ForbiddenArgumentException("Not enough balance! Account blocked");
		} 			
		throw new ForbiddenArgumentException("Not enough balance!");		
	}
	
	private MessageResponseDTO createMessageResponse(String message, Long amount) {
        return new MessageResponseDTO(message + amount);                
    }	
}
