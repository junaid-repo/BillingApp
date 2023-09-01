package com.billing.app.employees.services;

import com.billing.app.employees.dto.BaseOutput;
import com.billing.app.employees.entities.EmployeeDetails;
import com.billing.app.employees.entities.EmployeeSales;
import com.billing.app.employees.entities.UserCredential;
import com.billing.app.employees.repository.EmployeeSalesRepository;
import com.billing.app.employees.repository.EmployeeSaveRepository;
import com.billing.app.employees.repository.UserRegisterRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmployeeServices {

	@Autowired
	EmployeeSaveRepository empSaveRepo;

	@Autowired
	EmployeeSalesRepository empSalesRepo;

	@Autowired
	UserRegisterRepository userRegRepo;

	@Autowired
	PasswordEncoder psdEnocder;

	@Autowired
	JwtService jwtService;

	public BaseOutput registerUser(UserCredential user) {

		user.setPassword(psdEnocder.encode(user.getPassword()));
		UserCredential out = new UserCredential();
		BaseOutput response = new BaseOutput();
		String username = "";

		out = userRegRepo.save(user);

		if (out.getId() > 0) {
			username = user.getName().replaceAll("\\s", "").toLowerCase() + String.valueOf(out.getId());
		}

		out.setUsername(username);
		
		out=userRegRepo.save(out);

		response.setReturnCode(201l);
		response.setReturnMsg("User Created");
		response.setUsername(username);
		// TODO Auto-generated method stub
		return response;
	}

	public String generateToken(String username) {
		return jwtService.generateToken(username);

	}

	public void validateToken(String token) {
		jwtService.validateToken(token);
	}

	public BaseOutput updateEmployee(EmployeeDetails req) {

		BaseOutput response = new BaseOutput();
		EmployeeDetails out = new EmployeeDetails();
		String role = req.getRole();
		String username = "";
		out = empSaveRepo.save(req);
		if (role.equals("EMP"))
			username = "EMP000" + String.valueOf(out.getId());
		if (role.equals("CUS"))
			username = "CUS000" + String.valueOf(out.getId());

		out.setUsername(username);

		out = empSaveRepo.save(out);

		if (out.getId() < 0) {
			response.setReturnMsg("Something went wrong during save");
			response.setReturnCode(407l);
			return response;
		} else {
			response.setUsername(username);
			response.setReturnCode(201l);
			response.setReturnMsg("User created");
		}

		return response;
	}

	public BaseOutput updateEmployeeSales(EmployeeSales req) {
		BaseOutput response = new BaseOutput();
		LocalDateTime createdDate = LocalDateTime.now();
		req.setUpdatedDate(createdDate);
		EmployeeSales out = new EmployeeSales();
		out = empSalesRepo.save(req);

		if (out.getId() < 1) {
			response.setReturnMsg("Something went wrong");
			response.setReturnCode(444l);
			return response;
		}
		response.setReturnCode(201l);
		response.setReturnMsg("Updated");
		// response.setId(out.getId());

		return response;
	}

}
