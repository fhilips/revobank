package com.revobank.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revobank.model.Debit;

public interface DebitRepository extends JpaRepository<Debit, Long> {

}

