package com.billing.app.employees.services;

import com.billing.app.employees.dto.BaseOutput;
import com.billing.app.employees.entities.EmployeeDetails;
import com.billing.app.employees.repository.EmployeeSaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServices {

    @Autowired
    EmployeeSaveRepository empSaveRepo;

    public BaseOutput updateEmployee(EmployeeDetails req) {

        BaseOutput response = new BaseOutput();
        EmployeeDetails out = new EmployeeDetails();
        req.setRole("USER");
        out = empSaveRepo.save(req);

        if (out.getId() < 0) {
            response.setReturnMsg("Something went wrong during save");
            response.setReturnCode(407l);
            return response;
        } else {
            response.setId(out.getId());
            response.setReturnCode(201l);
            response.setReturnMsg("Employee created");
        }

        return response;
    }
}
