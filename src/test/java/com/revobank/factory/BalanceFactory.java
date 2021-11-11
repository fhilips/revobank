package com.revobank.factory;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.revobank.dto.DebitDTO;
import com.revobank.model.Account;
import com.revobank.model.Balance;
import com.revobank.model.Debit;

public class BalanceFactory {
	
	public static Balance createBalance(Account account) {
		return new Balance( account.getJobTitle().getInicialBalance(),
							Instant.now(),
							account														
							);		
	}

	public static DebitDTO createDebit() {
		return new DebitDTO(300.0, 1l);
	}
	
	public static List<Debit> createDebitList() {
		List<Debit> debitList = new ArrayList<Debit>();
		debitList.add(new Debit());
		debitList.add(new Debit());
		debitList.add(new Debit());
		return debitList;
	}
		
}
