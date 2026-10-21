package com.ledger.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ledger.entity.LedgerEntry;

@Repository
public interface LedgerEntryRepository extends JpaRepository<LedgerEntry, Long> {
	
	List<LedgerEntry> findAllByCustomer_IdOrderByTransactionAtDesc(Long customerId);
	
	Optional<LedgerEntry> findByIdAndCustomer_Id(Long entryId,Long customerId);
	
	boolean existsByReversalOf_Id(Long entryId);
}
