package com.billing.app.employees.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.core.Authentication;
import com.billing.app.employees.dto.AuthRequest;
import com.billing.app.employees.dto.BaseOutput;
import com.billing.app.employees.entities.EmployeeDetails;
import com.billing.app.employees.entities.EmployeeSales;
import com.billing.app.employees.entities.UserCredential;
import com.billing.app.employees.services.EmployeeServices;

@RestController
@RequestMapping("/bs/employee")
public class EmployeeController {

	@Autowired
	EmployeeServices serv;

	@Autowired
	AuthenticationManager authenticationManager;

	@PostMapping("/auth/register")
	ResponseEntity<BaseOutput> register(@RequestBody UserCredential user) {
		BaseOutput response = new BaseOutput();
		response = serv.registerUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PostMapping("/auth/generateToken")
	ResponseEntity<String> generateToken(@RequestBody AuthRequest au) {
		String response = "";
		Authentication authenticate = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(au.getUsername(), au.getPassword()));
		if (authenticate.isAuthenticated()) {
			response = serv.generateToken(au.getUsername());
		} else {
			throw new RuntimeException("Invalid access");
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping("/auth/validateToken/{token}")
	ResponseEntity<String> validateToken(@PathVariable String token) {

		serv.validateToken(token);

		return ResponseEntity.status(HttpStatus.CREATED).body("User is valid");
	}

	@PostMapping("/updateEmployee")
	ResponseEntity<BaseOutput> updateEmployee(@RequestBody EmployeeDetails req) {
		BaseOutput response = new BaseOutput();

		response = serv.updateEmployee(req);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PostMapping("/updateEmpSales")
	ResponseEntity<BaseOutput> updateEmployeeSales(@RequestBody EmployeeSales req) {
		BaseOutput response = new BaseOutput();

		response = serv.updateEmployeeSales(req);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}
