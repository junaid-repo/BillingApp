package com.billing.app.employees.repository;

import com.billing.app.employees.entities.EmployeeDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeSaveRepository extends JpaRepository<EmployeeDetails, Integer> {
}
