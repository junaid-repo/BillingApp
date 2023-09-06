package com.billing.app.accounts.externalapi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.billing.app.accounts.apiClassModels.AuthRequest;
import com.billing.app.accounts.apiClassModels.BaseOutput3;
import com.billing.app.accounts.apiClassModels.EmployeeSales;

@Service
@FeignClient(name="EMPLOYEE-SERVICE")
public interface EmpServiceFiengClient  {

    @PostMapping("/bs/employee/updateEmpSales")
    ResponseEntity<BaseOutput3> updateEmployeeSales(@RequestHeader(value = "Authorization", required = true)  String token,EmployeeSales req);
    
    @PostMapping("/bs/employee/auth/generateToken")
	ResponseEntity<String> generateToken(AuthRequest au) ;
}
