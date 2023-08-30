package com.billing.app.employees.repository;

import com.billing.app.employees.entities.EmployeeSales;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeSalesRepository extends JpaRepository<EmployeeSales, Integer> {
}
