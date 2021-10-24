package com.revobank.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.revobank.dto.AccountDTO;
import com.revobank.dto.response.MessageResponseDTO;
import com.revobank.model.Account;
import com.revobank.model.services.AccountService;

@Controller
@RequestMapping(value = "accounts")
public class AccountResource {
	
	private AccountService accountService;
	
	@Autowired
	public AccountResource(AccountService accountService) {
		this.accountService = accountService;
	}

	@GetMapping
	public ResponseEntity<List<Account>> getAllAccounts() {
		
		return null;		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public MessageResponseDTO createAccount(@RequestBody AccountDTO accountDTO) {
		
		return accountService.createNewAccount(accountDTO);		
	}
}
