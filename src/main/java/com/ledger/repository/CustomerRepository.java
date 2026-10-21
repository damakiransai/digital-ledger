package com.ledger.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ledger.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	List<Customer> findAllByBusiness_Id(Long businessId);
	
	Optional<Customer> findByIdAndBusiness_Id(Long customerId,Long businessId);
}
