package com.revobank.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import com.revobank.dto.AccountDTO;
import com.revobank.dto.AccountUpdateDTO;
import com.revobank.dto.filters.AccountFilter;
import com.revobank.dto.response.MessageResponseDTO;
import com.revobank.services.AccountService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "v1/accounts")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AccountResource {
	
	private AccountService accountService;
		
	@PostMapping(value = "/create")	
	@ResponseStatus(HttpStatus.CREATED)
	public MessageResponseDTO create(@Valid @RequestBody AccountDTO accountDTO) {			
		MessageResponseDTO messageAccountCreated = accountService.createAccount(accountDTO);
		return messageAccountCreated;		
	}
	
	@PutMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public MessageResponseDTO update(@Valid @PathVariable Long id, @RequestBody AccountUpdateDTO dto) {
		MessageResponseDTO messageAccountUpdated = accountService.updateByDto(id, dto);		
		return messageAccountUpdated;
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<AccountDTO> findById(@PathVariable Long id) {
		AccountDTO dto = this.accountService.findById(id);		 
		return ResponseEntity.ok().body(dto);		
	}	
	
	@GetMapping
	public ResponseEntity<Page<AccountDTO>> getAllPaged(Pageable page) {
		Page<AccountDTO> allAccounts = accountService.getAllAccountsPaged(page);
		return ResponseEntity.ok().body(allAccounts);		
	}
	
	@GetMapping("/search")
    public ResponseEntity<List<AccountDTO>> findAllPaged(AccountFilter filter) {
		List<AccountDTO> findAllPageable = this.accountService.findAllPageable(filter);
        return ResponseEntity.ok().body(findAllPageable);        
    }
		
}
