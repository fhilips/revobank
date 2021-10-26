package com.revobank.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revobank.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

	

}

