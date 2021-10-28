package com.revobank.factory;

import com.revobank.dto.DebitDTO;

public class BalanceFactory {

	public static DebitDTO createDebit() {
		return new DebitDTO(300.0, 1l);
	}
		
}
