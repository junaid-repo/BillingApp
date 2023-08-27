package com.billing.app.employees.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="BSEmployees")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class EmployeeDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private String gender;
    private String phone;

    private String password;
    private String role;
}
