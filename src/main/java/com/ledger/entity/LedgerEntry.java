package com.ledger.entity;

import java.math.BigDecimal;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ledger.enums.EntryType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;


@Table(name = "ledger_entry")
@Entity
public class LedgerEntry {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
	@JsonIgnore
	private Customer customer;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "entry_type", nullable = false, length=20)
	private EntryType entryType;
	
	@Column(nullable = false, precision = 19, scale = 2)
	private BigDecimal amount;
	
	@Column(length = 255)
	private String description;
	
	@Column(name = "created_at", nullable = false, updatable = false)
	private Instant createdAt;
	
	@Column(name = "transaction_at", nullable = false)
    private Instant transactionAt;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reversal_of_id", unique = true)
	private LedgerEntry reversalOf;
	
	@PrePersist
    protected void onCreate() {
        createdAt = Instant.now();

        if (transactionAt == null) {
            transactionAt = createdAt;
        }
    }
	
	
	
	public Customer getCustomer() {
		return customer;
	}



	public void setCustomer(Customer customer) {
		this.customer = customer;
	}



	public EntryType getEntryType() {
		return entryType;
	}



	public void setEntryType(EntryType entryType) {
		this.entryType = entryType;
	}



	public BigDecimal getAmount() {
		return amount;
	}



	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public Instant getTransactionAt() {
		return transactionAt;
	}



	public void setTransactionAt(Instant transactionAt) {
		this.transactionAt = transactionAt;
	}



	public LedgerEntry getReversalOf() {
		return reversalOf;
	}



	public void setReversalOf(LedgerEntry reversalOf) {
		this.reversalOf = reversalOf;
	}



	public LedgerEntry() {
		// TODO Auto-generated constructor stub
	}

}
