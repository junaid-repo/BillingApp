package com.billing.app.stocks.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "StocksDetails")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StockDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id=0;
    private String category=null;
    private String brand=null;
    private String model=null;
    private Long units;
    private Double costPerUnit;
    private String shelfLocation;
    private String status=null;
    private LocalDateTime updatedDate;




}
