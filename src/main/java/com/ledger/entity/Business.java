package com.ledger.entity;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "business")
public class Business {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "business_name",nullable = false,length = 150)
	private String businessName;
	
	@Column(name = "merchant_name",nullable = false,length = 25)
	private String merchantName;
	
	@Column(length = 20)
	private String phone;
	
	@Column(length = 255)
	private String email;
	
	@OneToMany(mappedBy = "business")
	private List<Customer> customers=new ArrayList<>();
	
	@Column(name="created_at",nullable = false,updatable = false)
	private Instant createdAt;
	
	@PrePersist
	protected void onCreate() {
		this.createdAt=Instant.now();
	}
	
	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
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

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public Business() {
		// TODO Auto-generated constructor stub
	}

}
