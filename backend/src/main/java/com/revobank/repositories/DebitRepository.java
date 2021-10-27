package com.revobank.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revobank.model.Debit;

public interface DebitRepository extends JpaRepository<Debit, Long> {

	List<Debit> findAllByBalanceId(Long id);

}

