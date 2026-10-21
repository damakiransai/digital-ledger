package com.ledger.controller;


import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ledger.entity.Customer;
import com.ledger.service.CustomerService;

@RestController
@RequestMapping("/api/businesses")
public class CustomerController {

	private final CustomerService customerService;
	
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;

	}
	
	@PostMapping("/{businessId}/customers")
	public Customer createCustomer(@PathVariable("businessId") Long businessId
			,@RequestBody Customer customer) {
		return customerService.createCustomer(businessId, customer);
	}
	
	@GetMapping("/{businessId}/customers")
	public List<Customer> getCustomers(@PathVariable("businessId") Long businessId){
		return customerService.getCustomersByBusiness(businessId);
	}
	
	@GetMapping("/{businessId}/customers/{customerId}")
	public Customer getCustomer(@PathVariable("businessId") Long businessId,
			@PathVariable("customerId") Long customerId) {
		return customerService.getCustomerById(businessId, customerId);
	}

	@PutMapping("/{businessId}/customers/{customerId}")
	public Customer updateCustomer(@PathVariable("businessId") Long businessId,
			@PathVariable("customerId") Long customerId,@RequestBody Customer updatedCustomer) {
		return customerService.updateCustomer(businessId, customerId, updatedCustomer);
	}
}
