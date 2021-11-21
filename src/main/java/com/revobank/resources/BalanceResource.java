package com.revobank.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revobank.dto.BalanceDTO;
import com.revobank.dto.DebitDTO;
import com.revobank.dto.response.MessageResponseDTO;
import com.revobank.services.BalanceService;
import com.revobank.services.DebitService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "v1/balances")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BalanceResource {
	
	private BalanceService balanceService;
	
	private DebitService debitService;
	
	@GetMapping
	public ResponseEntity<List<BalanceDTO>> getAll() {
		List<BalanceDTO> allBalances = balanceService.getAllBalances();
		return ResponseEntity.ok().body(allBalances);		
	}
	
	@GetMapping(value = "/{accountId}")
	public ResponseEntity<BalanceDTO> findById(@PathVariable Long accountId) throws Exception {
		BalanceDTO balanceDto = balanceService.findByAccountID(accountId);
		return ResponseEntity.ok().body(balanceDto);		
	}
	
	@PostMapping(value = "/debit")	
	@ResponseStatus(HttpStatus.OK)
	public MessageResponseDTO addDebit(@RequestBody @Valid DebitDTO dto) {		
		MessageResponseDTO messageAddDebit = debitService.addDebit(dto);
		return messageAddDebit;		
	}
	
	@GetMapping(value = "{accountId}/debits")	
	public ResponseEntity<List<DebitDTO>> getAllDebitsByAccountId(@PathVariable Long accountId) {
		List<DebitDTO> allDebits = debitService.getAllDebitsByAccountId(accountId);
		
		return ResponseEntity.ok().body(allDebits);		
	}
		
}
