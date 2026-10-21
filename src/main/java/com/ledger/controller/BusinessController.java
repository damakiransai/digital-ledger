package com.ledger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ledger.entity.Business;
import com.ledger.service.BusinessService;

@RestController
@RequestMapping("/api/businesses")
public class BusinessController {
	
	private final BusinessService businessService;
	
	@Autowired
	public BusinessController(BusinessService businessService) {
		this.businessService = businessService;
	}
	
	//create a new Business
	@PostMapping
	public Business createBusiness(@RequestBody Business business) {
		return businessService.createBusiness(business);
	}
	
	@GetMapping("/{businessId}")
	public Business getBusiness(@PathVariable("businessId") Long businessId) {
		return businessService.getBusinessById(businessId);
	}
	
	@PutMapping("/{businessId}")
	public Business updateBusiness(@PathVariable("businessId") Long businessId,@RequestBody Business business) {
		return businessService.updateBusiness(businessId, business);
	}

}
