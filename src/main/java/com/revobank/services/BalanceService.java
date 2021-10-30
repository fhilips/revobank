package com.revobank.services;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revobank.dto.BalanceDTO;
import com.revobank.dto.DebitDTO;
import com.revobank.exceptions.ForbiddenArgumentException;
import com.revobank.exceptions.ResourceNotFoundException;
import com.revobank.mappers.BalanceMapper;
import com.revobank.model.Account;
import com.revobank.model.Balance;
import com.revobank.model.enums.Status;
import com.revobank.repositories.BalanceRepository;

@Service
public class BalanceService{

	BalanceRepository balanceRepository;
	
	@Autowired
	public BalanceService(BalanceRepository repository) {
		this.balanceRepository = repository;
	}

	@Transactional
	public void createBalance(Account account) {
		Balance entity = new Balance();	
		
		entity.setAccount(account);
		entity.setBalance(account.getJobTitle().getInicialBalance());
		entity.setUpdatedAt(Instant.now());
		balanceRepository.save(entity);
	}
	
	@Transactional
	public void updateTotalAmount(DebitDTO dto) {
		Balance entity = verifyIfExists(dto.getAccountId());			
			
		if(dto.getAmount() > entity.getBalance()) {
			throw new ForbiddenArgumentException("Not enough balance!");
		}
		Double amount = (entity.getBalance() - dto.getAmount());
		entity.setBalance(amount);
		entity.setUpdatedAt(Instant.now());
		balanceRepository.save(entity);	
	}
	
	@Transactional(readOnly = true)
	public List<BalanceDTO> getAllBalances() {
		List<Balance> entities = balanceRepository.findAllBalancesWhereAccountStatusIsActive();		
		return BalanceMapper.toListDto(entities);		
	}

	@Transactional(readOnly = true)
	public BalanceDTO findByAccountID(Long id){
		Balance entity = verifyIfExists(id);
		verifyIfAccountBlocked(entity.getAccount().getStatus());
		return BalanceMapper.toDto(entity);
	}	
	
	@Transactional(readOnly = true)
	public Balance findById(Long id){
		Balance entity = balanceRepository.findById(id).orElseThrow(() -> 
								new ResourceNotFoundException("Balance not found!"));		
		return entity;
	}
	
	public Balance findEntityByAccountID(Long accountId) {
		return verifyIfExists(accountId);		
	}		

	public BalanceDTO findByBalanceID(Long id){
		Balance entity = verifyIfExists(id);        
		return BalanceMapper.toDto(entity); 
	}

	@Transactional(readOnly = true)
	private Balance verifyIfExists(Long id) throws RuntimeException {		
		return balanceRepository.findByAccountId(id).orElseThrow(() -> 
											new ResourceNotFoundException("Account not found!"));
	}
	
	public void verifyIfAccountBlocked(Status status) {		
		if(status.equals(Status.BLOCKED)) {			
			throw new ForbiddenArgumentException("Account is blocked!");
		}
	}	
	
}
