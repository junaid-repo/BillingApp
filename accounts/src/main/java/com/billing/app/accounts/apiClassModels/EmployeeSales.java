package com.billing.app.accounts.apiClassModels;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@ToString
@Getter
@Setter
public class EmployeeSales {


    private int id;
    private int empId;
    private LocalDateTime updatedDate;
    private String customerCode;
    private Double amount;



}
