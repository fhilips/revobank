package com.revobank.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revobank.dto.AccountDTO;
import com.revobank.dto.response.MessageResponseDTO;
import com.revobank.model.repositories.AccountRepository;

@Service
public class AccountService{
	
	private AccountRepository repository;
	
	@Autowired
	public AccountService(AccountRepository repository) {		
		this.repository = repository;
	}

	public MessageResponseDTO createNewAccount(AccountDTO accountDTO) {
		
		return null;
	}

}
