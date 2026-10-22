package com.ledger.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ledger.entity.Customer;
import com.ledger.entity.LedgerEntry;
import com.ledger.enums.EntryType;
import com.ledger.repository.CustomerRepository;
import com.ledger.repository.LedgerEntryRepository;
import com.ledger.service.LedgerService;

@Service
public class LedgerServiceImpl implements LedgerService {

	private final LedgerEntryRepository ledgerEntryRepository;
	private final CustomerRepository customerRepository;
	
	public LedgerServiceImpl(LedgerEntryRepository ledgerEntryRepository, CustomerRepository customerRepository) {
		this.ledgerEntryRepository = ledgerEntryRepository;
		this.customerRepository = customerRepository;
	}
	
	private Customer getCustomer(Long businessId,Long customerId) {
		return customerRepository.findByIdAndBusiness_Id(customerId, businessId)
				.orElseThrow(()->new
						RuntimeException("Customer Not Found"));
	}

	@Override
	public LedgerEntry recordEntry(Long businessId, Long customerId, LedgerEntry entry) {
		//get customer to add to the ledger
		Customer customer=getCustomer(businessId, customerId);
		
		//validate EntryType and Amount
		if(entry.getEntryType()!=EntryType.CREDIT && entry.getEntryType()!=EntryType.PAYMENT ) {
			throw new IllegalArgumentException("only CREDIT and PAYMENT can be recorded directly");
		}
		if(entry.getAmount()==null || entry.getAmount().compareTo(BigDecimal.ZERO)<=0) {
			throw new IllegalArgumentException("amount must be greater then zero");
		}
		entry.setCustomer(customer);
		entry.setReversalOf(null);
		
		return ledgerEntryRepository.save(entry);
	}

	@Override
	public List<LedgerEntry> getLedgerEntries(Long businessId, Long customerId) {
		//check weather customer exist or not
		getCustomer(businessId, customerId);
		
		List<LedgerEntry> entries=ledgerEntryRepository.findAllByCustomer_IdOrderByTransactionAtDesc(customerId);
		return entries;
	}

	@Override
	public BigDecimal calculateBalance(Long businessId, Long customerId) {
		//check customer exist or not
		getCustomer(businessId, customerId);
		
		List<LedgerEntry> entries=ledgerEntryRepository.findAllByCustomer_IdOrderByTransactionAtDesc(customerId);
		
		BigDecimal balance=BigDecimal.ZERO;
		
		for(LedgerEntry entry:entries) {
			EntryType currEntryType=entry.getEntryType();
			
			if(currEntryType==EntryType.CREDIT) {
				balance=balance.add(entry.getAmount());
				
			}else if (currEntryType==EntryType.PAYMENT) {
				balance=balance.subtract(entry.getAmount());
				
			}else if (currEntryType==EntryType.REVERSAL) {
				LedgerEntry original=entry.getReversalOf();
				
				//check if the current reversal is undoing CREDIT OR DEBIT and perform Operation accordingly
				if(original.getEntryType()==EntryType.CREDIT) {
					balance=balance.subtract(entry.getAmount());
					
				}else if (original.getEntryType()==EntryType.PAYMENT) {
					balance=balance.add(entry.getAmount());
					
				}
			}
		}
		
		return balance;
	}

	@Override
	public LedgerEntry reversEntry(Long businessId, Long customerId, Long entryId) {
		//fetch customer
		Customer customer=getCustomer(businessId, customerId);
		
		//Find the original id(entryId)
		LedgerEntry originaLedgerEntry=ledgerEntryRepository.findByIdAndCustomer_Id(entryId, customerId)
				.orElseThrow(()->new RuntimeException("Ledger entry not found"));
		
		//check if original entry is already an reversal
		if(originaLedgerEntry.getEntryType()==EntryType.REVERSAL) {
			throw new IllegalArgumentException("A reversal Entry cannot be reversed");
		}
		
		if(ledgerEntryRepository.existsByReversalOf_Id(entryId)) {
			throw new IllegalArgumentException("This entry has already been reversed");
		}
		
		LedgerEntry reversaLedgerEntry=new LedgerEntry();
		
		reversaLedgerEntry.setAmount(originaLedgerEntry.getAmount());
		reversaLedgerEntry.setEntryType(EntryType.REVERSAL);
		reversaLedgerEntry.setCustomer(customer);
		reversaLedgerEntry.setReversalOf(originaLedgerEntry);
		reversaLedgerEntry.setDescription("This is Reversal of Entry #"+entryId);
		
		return ledgerEntryRepository.save(reversaLedgerEntry);
	}

}
