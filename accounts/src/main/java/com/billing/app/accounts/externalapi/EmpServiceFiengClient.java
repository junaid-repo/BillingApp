package com.billing.app.accounts.externalapi;

import com.billing.app.accounts.apiClassModels.AuthRequest;
import com.billing.app.accounts.apiClassModels.BaseOutput2;
import com.billing.app.accounts.apiClassModels.BaseOutput3;
import com.billing.app.accounts.apiClassModels.EmployeeSales;
import com.billing.app.accounts.dto.BaseOutput;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@FeignClient(name="EMPLOYEE-SERVICE")
public interface EmpServiceFiengClient  {

    @PostMapping("/bs/employee/updateEmpSales")
    ResponseEntity<BaseOutput3> updateEmployeeSales(EmployeeSales req);
    
    @PostMapping("/bs/employee/auth/generateToken")
	ResponseEntity<String> generateToken(AuthRequest au) ;
}
