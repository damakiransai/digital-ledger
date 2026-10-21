package com.ledger.entity;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="customer_name",nullable = false,length = 100)
	private String customerName;
	
	@Column(length = 20)
	private String phone;
	
	@Column(length = 255)
	private String email;
	
	@Column(name = "created_at",nullable = false,updatable = false)
	private Instant createdAt;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "business_id",nullable = false)
	private Business business;
	
	@OneToMany(mappedBy = "customer")
	private List<LedgerEntry> ledgerEntries=new ArrayList<>();
	
	@PrePersist
	protected void onCreate() {
		createdAt=Instant.now();
	}

	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Business getBusiness() {
		return business;
	}

	public void setBusiness(Business business) {
		this.business = business;
	}

	public List<LedgerEntry> getLedgerEntries() {
		return ledgerEntries;
	}

	public void setLedgerEntries(List<LedgerEntry> ledgerEntries) {
		this.ledgerEntries = ledgerEntries;
	}
	
	

}
