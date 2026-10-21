package com.ledger.service;

import com.ledger.entity.Business;

public interface BusinessService {
	
	Business createBusiness(Business business);
	
	Business getBusinessById(Long businessId);
	
	Business updateBusiness(Long businessId,Business updatedBusiness);
}
