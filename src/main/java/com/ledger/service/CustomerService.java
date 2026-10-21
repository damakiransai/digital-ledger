package com.ledger.service;

import java.util.List;

import com.ledger.entity.Customer;

public interface CustomerService {
	
	Customer createCustomer(Long businessId,Customer customer);
	
	Customer getCustomerById(Long businessId,Long customerId);
	
	List<Customer> getCustomersByBusiness(Long businessId);
	
	Customer updateCustomer(Long businessId,Long customerId,Customer updatedCustomer);
}
