package com.revobank.factory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.revobank.dto.DebitDTO;

public class BalanceFactory {

	public static DebitDTO createDebit() {
		return new DebitDTO(300.0, 1l);
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
