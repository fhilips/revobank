package com.revobank.factory;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.revobank.dto.AccountDTO;
import com.revobank.model.Account;
import com.revobank.model.enums.JobTitle;
import com.revobank.model.enums.Status;

public class AccountFactory {

	public static Account createAccount() {
		return new Account(1l, "Filipe", "41652034838", stringToLocalDate("09/12/1993"),
				JobTitle.COUNTER, Status.ACTIVE, Instant.now(), Instant.now());
	}
	
	
	public static AccountDTO createAccountDTO() {
		Account account = createAccount();
		return new AccountDTO(account.getId(), account.getName(), account.getDocument(),
				localDateToString(account.getBirthDate()), account.getJobTitle(), account.getStatus(),
				account.getCreatedAt(), account.getUpdatedAt());
	}
	
	private static LocalDate stringToLocalDate(String date) {		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");		
		return LocalDate.parse(date, formatter);
	}
	
	private static String localDateToString(LocalDate localDate) {		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String formattedDate = localDate.format(formatter);
		return formattedDate;
	}
	
}
