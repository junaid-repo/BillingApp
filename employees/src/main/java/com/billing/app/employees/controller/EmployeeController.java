package com.billing.app.employees.controller;

import com.billing.app.employees.dto.BaseOutput;
import com.billing.app.employees.entities.EmployeeDetails;
import com.billing.app.employees.entities.EmployeeSales;
import com.billing.app.employees.services.EmployeeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bs/employee")
public class EmployeeController {

    @Autowired
    EmployeeServices serv;

    @PostMapping("/updateEmployee")
    ResponseEntity<BaseOutput> updateEmployee(@RequestBody EmployeeDetails req){
        BaseOutput response = new BaseOutput();

        response=serv.updateEmployee(req);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PostMapping("/updateEmpSales")
    ResponseEntity<BaseOutput> updateEmployeeSales(@RequestBody EmployeeSales req){
        BaseOutput response= new BaseOutput();

        response=serv.updateEmployeeSales(req);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
