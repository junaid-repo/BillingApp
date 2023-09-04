package com.billing.customers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.billing.customers.entities.CustomerDetails;

public interface CustomerRepository extends JpaRepository<CustomerDetails, Integer>{

	CustomerDetails findByCustomerCode(String custCode);

}
