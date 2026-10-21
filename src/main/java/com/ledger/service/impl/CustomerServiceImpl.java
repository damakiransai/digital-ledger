package com.ledger.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ledger.entity.Business;
import com.ledger.entity.Customer;
import com.ledger.repository.BusinessRepository;
import com.ledger.repository.CustomerRepository;
import com.ledger.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository customerRepository;
	private final BusinessRepository businessRepository;
	
	public CustomerServiceImpl(CustomerRepository customerRepository, BusinessRepository businessRepository) {
		this.customerRepository = customerRepository;
		this.businessRepository = businessRepository;
	}

	@Override
	public Customer createCustomer(Long businessId, Customer customer) {
		Business business=businessRepository.findById(businessId)
				.orElseThrow(()->
								new RuntimeException("Business no found"));
		customer.setBusiness(business);
	
		return customerRepository.save(customer);
	}

	@Override
	public Customer getCustomerById(Long businessId, Long customerId) {
		Customer customer=customerRepository.findByIdAndBusiness_Id(customerId, businessId)
					.orElseThrow(()->
							new RuntimeException("Customer Not Found"));
		return customer;
	}

	@Override
	public List<Customer> getCustomersByBusiness(Long businessId) {
		
		if(!businessRepository.existsById(businessId)) {
			throw new RuntimeException("Business not found");
		}
		
		List<Customer> customers=customerRepository.findAllByBusiness_Id(businessId);
		
		return customers;
	}

	@Override
	public Customer updateCustomer(Long businessId, Long customerId, Customer updatedCustomer) {

		Customer existingCustomer=customerRepository.findByIdAndBusiness_Id(customerId, businessId)
		.orElseThrow(()->
				new RuntimeException("Customer Not Found"));
		
		existingCustomer.setCustomerName(updatedCustomer.getCustomerName());;
		existingCustomer.setPhone(updatedCustomer.getPhone());
		existingCustomer.setEmail(updatedCustomer.getEmail());
		
		return customerRepository.save(existingCustomer);
	}

}
