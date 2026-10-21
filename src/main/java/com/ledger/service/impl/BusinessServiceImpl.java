package com.ledger.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ledger.entity.Business;
import com.ledger.repository.BusinessRepository;
import com.ledger.service.BusinessService;

@Service
public class BusinessServiceImpl implements BusinessService {
	
	private final BusinessRepository businessRepository;

	@Autowired
	public BusinessServiceImpl(BusinessRepository businessRepository) {
		this.businessRepository = businessRepository;
	}

	@Override
	public Business createBusiness(Business business) {
		return businessRepository.save(business);
	}

	@Override
	public Business getBusinessById(Long businessId) {
		Business business=businessRepository.findById(businessId)
				.orElseThrow(()->new RuntimeException(
						"Business not found with ID:"+businessId)
				);
		return business;
	}

	@Override
	public Business updateBusiness(Long businessId, Business updatedBusiness) {
		Optional<Business> resultOptional=businessRepository.findById(businessId);
		if(resultOptional.isPresent()) {
			Business existingBusiness=resultOptional.get();
			
			existingBusiness.setBusinessName(updatedBusiness.getBusinessName());
			existingBusiness.setMerchantName(updatedBusiness.getMerchantName());
			existingBusiness.setPhone(updatedBusiness.getPhone());
			existingBusiness.setEmail(updatedBusiness.getEmail());
			
			return businessRepository.save(existingBusiness);
		}
		throw new RuntimeException("Business not found with ID:"+businessId);
	}

}
