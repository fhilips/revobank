package com.revobank.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.revobank.dto.AccountDTO;
import com.revobank.dto.AccountUpdateDTO;
import com.revobank.dto.response.MessageResponseDTO;
import com.revobank.model.services.AccountService;

@RestController
@RequestMapping(value = "accounts")
public class AccountResource {
	
	private AccountService accountService;
	
	@Autowired
	public AccountResource(AccountService accountService) {
		this.accountService = accountService;
	}
	
	@PostMapping(value = "/create")	
	@ResponseStatus(HttpStatus.CREATED)
	public MessageResponseDTO createAccount(@Valid @RequestBody AccountDTO accountDTO, UriComponentsBuilder uriBuilder) {			
		return accountService.createAccount(accountDTO); 		
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<MessageResponseDTO> updateAccount(@Valid @PathVariable Long id, @RequestBody AccountUpdateDTO dto) {
		MessageResponseDTO responseMessage = accountService.updateByDto(id, dto);		
		return ResponseEntity.ok().body(responseMessage);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<AccountDTO> findAccountById(@PathVariable Long id) {
		AccountDTO dto = this.accountService.findById(id);		 
		return ResponseEntity.ok().body(dto);		
	}
	
	@GetMapping
	public ResponseEntity<List<AccountDTO>> getAllAccounts() {
		List<AccountDTO> allAccounts = accountService.getAllAccounts();
		return ResponseEntity.ok().body(allAccounts);		
	}
		
}
