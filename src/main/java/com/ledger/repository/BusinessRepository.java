package com.ledger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ledger.entity.Business;

@Repository
public interface BusinessRepository extends JpaRepository<Business, Long> {

}
