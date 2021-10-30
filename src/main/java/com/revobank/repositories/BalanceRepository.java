package com.revobank.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.revobank.model.Balance;

public interface BalanceRepository extends JpaRepository<Balance, Long> {

	Optional<Balance> findByAccountId(Long id);

	@Query(nativeQuery = true, value = 
			"SELECT b.balance, b.updated_at, b.account_id "
			+ "FROM tb_balance b "
			+ "INNER JOIN tb_account a ON b.account_id = a.id "
			+ "WHERE a.status = 'ACTIVE'")
	List<Balance> findAllBalancesWhereAccountStatusIsActive();
	
	
}

