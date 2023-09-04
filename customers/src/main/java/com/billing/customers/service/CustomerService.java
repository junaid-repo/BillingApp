package com.billing.customers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import com.billing.customers.dto.BaseOutput;
import com.billing.customers.entities.CustomerDetails;
import com.billing.customers.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	CustomerRepository custRepo;

	public BaseOutput saveCustomer(CustomerDetails req) {

		BaseOutput response = new BaseOutput();

		CustomerDetails out = new CustomerDetails();
		out = custRepo.save(req);

		String custCode = "";

		custCode = "CUST0000" + String.valueOf(out.getId());

		out.setCustomerCode(custCode);

		out = custRepo.save(out);

		if (out.getId() < 1) {
			response.setCustomerCode("na");
			response.setReturnCode(444l);
			response.setReturnMsg("Something went wrong");
			return response;
		}
		response.setCustomerCode(custCode);
		response.setReturnCode(302l);
		response.setReturnMsg(HttpStatus.CREATED.toString());

		return response;
	}

	public CustomerDetails getCustomerDetailsByCode(String custCode) {

		return custRepo.findByCustomerCode(custCode);
	}

}
