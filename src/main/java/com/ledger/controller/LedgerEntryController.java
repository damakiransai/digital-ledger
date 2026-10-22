package com.ledger.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ledger.entity.LedgerEntry;
import com.ledger.service.LedgerService;

@RestController
@RequestMapping("/api/businesses")
public class LedgerEntryController {

	private final LedgerService ledgerService;

	public LedgerEntryController(LedgerService ledgerService) {
		this.ledgerService = ledgerService;
	}

	@PostMapping("/{businessId}/customers/{customerId}/ledger-entries")
	public LedgerEntry recordEntry(@PathVariable("businessId") Long businessId,@PathVariable("customerId") Long customerId, @RequestBody LedgerEntry entry) {
		
		return ledgerService.recordEntry(businessId, customerId, entry);
	}

	@GetMapping("/{businessId}/customers/{customerId}/ledger-entries")
	public List<LedgerEntry> getCustomerLedgerEntries(@PathVariable("businessId") Long businessId,@PathVariable("customerId") Long customerId) {
		
		return ledgerService.getLedgerEntries(businessId, customerId);
	}

	@GetMapping("/{businessId}/customers/{customerId}/balance")
	public BigDecimal getCustomerBalance(@PathVariable("businessId") Long businessId,@PathVariable("customerId") Long customerId) {
		
		return ledgerService.calculateBalance(businessId, customerId);
	}

	@PostMapping("/{businessId}/customers/{customerId}/ledger-entries/{entryId}/reversal")
	public LedgerEntry reversEntry(@PathVariable("businessId") Long businessId,@PathVariable("customerId") Long customerId, @PathVariable("entryId") Long entryId) {

		return ledgerService.reversEntry(businessId, customerId, entryId);
	}

}
