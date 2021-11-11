package com.revobank.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revobank.dto.AccountDTO;
import com.revobank.dto.DebitDTO;
import com.revobank.dto.response.MessageResponseDTO;
import com.revobank.exceptions.ForbiddenArgumentException;
import com.revobank.mappers.DebitMapper;
import com.revobank.model.Balance;
import com.revobank.model.Debit;
import com.revobank.model.enums.JobTitle;
import com.revobank.model.enums.Status;
import com.revobank.repositories.DebitRepository;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DebitService{

	public DebitRepository debitRepository;
	
	public BalanceService balanceService;
	
	public AccountService accountService;

	@Transactional
	public MessageResponseDTO addDebit(DebitDTO dto) {
		
		Balance balanceEntity = balanceService.findEntityByAccountID(dto.getAccountId());
		Debit debitEntity = DebitMapper.toEntity(dto, balanceEntity);
				
		Status accountStatus = balanceEntity.getAccount().getStatus();
		JobTitle jobTitle = balanceEntity.getAccount().getJobTitle();
		
		balanceService.verifyIfAccountBlocked(accountStatus);
		
		if(dto.getAmount() > balanceEntity.getBalance()) {
			verifyIfAccountShouldBeBlocked(dto.getAccountId(), jobTitle);		
			return createMessageResponse("Error to create debit on balance ID ", debitEntity.getBalance().getId());
		} else {			
			balanceService.updateTotalAmount(dto);
			debitRepository.save(debitEntity);
			return createMessageResponse("Debit created on balance ID ", debitEntity.getBalance().getId());
		}							
	}	

	@Transactional(readOnly = true)
	public List<DebitDTO> getAllDebitsByAccountId(Long id) {
		AccountDTO account = accountService.findById(id);
		balanceService.verifyIfAccountBlocked(account.getStatus());
		
		List<Debit> allDebits = debitRepository.findAllByBalanceId(id);
		return DebitMapper.toListDto(allDebits);
	}
		
	private void verifyIfAccountShouldBeBlocked(Long accountId, JobTitle jobTitle) {
		if(jobTitle.shouldBlockByInvalidAmount()) {
			accountService.blockAccount(accountId);
			throw new ForbiddenArgumentException("Not enough balance! Account blocked");
		} 			
		throw new ForbiddenArgumentException("Not enough balance!");		
	}
	
	private MessageResponseDTO createMessageResponse(String message, Long id) {
        return new MessageResponseDTO(message + id);                
    }	
}
