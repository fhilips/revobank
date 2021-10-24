package com.revobank.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.revobank.dto.DebitDTO;
import com.revobank.model.Balance;
import com.revobank.model.services.BalanceService;
import com.revobank.model.services.DebitService;

@Controller
@RequestMapping(value = "balances")
public class BalanceResource {
	
	private BalanceService balanceService;
	
	private DebitService debitService;
	
	@Autowired
	public BalanceResource(BalanceService balanceService, DebitService debitService) {		
		this.balanceService = balanceService;
		this.debitService = debitService;
	}

	@GetMapping()
	public ResponseEntity<List<Balance>> getAllBalances() {
		
		return null;		
	}
	
	@GetMapping(value = "/balance-by-account")
	public ResponseEntity<List<Balance>> getBalanceByAccount(@RequestParam(defaultValue = "") String account) {
		
		return null;		
	}
	
	@PostMapping(value = "/debit")	
	public ResponseEntity<List<Balance>> addDebit(@RequestBody DebitDTO dto) {
		debitService.addDebit(dto);
		return null;		
	}
}
