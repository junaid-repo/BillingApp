package com.billing.app.accounts.apiClassModels;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StockDetails {


    Integer id=0;
    private String category;
    private String brand;
    private String model;
    private Long units;
    private Double costPerUnit;
    private String shelfLocation;
    private String status;




}
