package com.ledger.service;

import java.math.BigDecimal;
import java.util.List;

import com.ledger.entity.LedgerEntry;

public interface LedgerService {
	LedgerEntry recordEntry(Long businessId,Long customerId,LedgerEntry entry);
	
	List<LedgerEntry> getLedgerEntries(Long businessId,Long customerId);
	
	BigDecimal calculateBalance(Long businessId,Long customerId);
	
	LedgerEntry reversEntry(Long businessId,Long customerId,Long entryId);
}
