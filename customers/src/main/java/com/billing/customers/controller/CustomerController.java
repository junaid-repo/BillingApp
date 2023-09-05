package com.billing.customers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.billing.customers.dto.BaseOutput;
import com.billing.customers.entities.CustomerDetails;
import com.billing.customers.service.CustomerService;

@RestController
@RequestMapping("/bs/customer")
public class CustomerController {
	
	@Autowired
	CustomerService serv;
	
	@PostMapping("/save")
	ResponseEntity<BaseOutput> saveCustomer(@RequestBody CustomerDetails req){
		
		BaseOutput response = new BaseOutput();
		
		response=serv.saveCustomer(req);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	@GetMapping("/get/{custCode}")
	ResponseEntity<CustomerDetails> getCustomer(@PathVariable String custCode){
		
		CustomerDetails response = new CustomerDetails();
		
		response=serv.getCustomerDetailsByCode(custCode);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

}
