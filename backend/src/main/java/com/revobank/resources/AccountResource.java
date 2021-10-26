package com.revobank.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import com.revobank.dto.AccountDTO;
import com.revobank.model.services.AccountService;

@Controller
@RequestMapping(value = "accounts")
public class AccountResource {
	
	private AccountService accountService;
	
	@Autowired
	public AccountResource(AccountService accountService) {
		this.accountService = accountService;
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<AccountDTO> findById(@PathVariable Long id) {
		AccountDTO dto = this.accountService.findById(id);		 
		return ResponseEntity.ok().body(dto);		
	}
	
	@PostMapping(value = "/create")	
	public ResponseEntity<?> createAccount(@RequestBody @Valid AccountDTO accountDTO, UriComponentsBuilder uriBuilder) {
		AccountDTO newDto = accountService.createAccount(accountDTO);	
		
		URI uri = uriBuilder.path("/accounts/create/{id}").buildAndExpand(newDto.getId()).toUri();
		
		return ResponseEntity.created(uri).body(newDto); 		
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<AccountDTO> update(@PathVariable @Valid Long id, @RequestBody AccountDTO dto) {
		AccountDTO newDto = accountService.updateByDto(id, dto);		
		return ResponseEntity.ok().body(newDto);
	}
	
	@GetMapping
	public ResponseEntity<List<AccountDTO>> getAllAccounts() {
		List<AccountDTO> allAccounts = accountService.getAllAccounts();
		return ResponseEntity.ok().body(allAccounts);		
	}
		
	
	
}
