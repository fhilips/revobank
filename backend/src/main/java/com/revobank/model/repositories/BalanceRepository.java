package com.revobank.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revobank.model.Balance;

public interface BalanceRepository extends JpaRepository<Balance, Long> {

}

