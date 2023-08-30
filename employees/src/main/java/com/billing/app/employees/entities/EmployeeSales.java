package com.billing.app.employees.entities;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name="EmployeeSales")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class EmployeeSales {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int empId;
    private LocalDateTime updatedDate;
    private String customerCode;
    private Double amount;



}
