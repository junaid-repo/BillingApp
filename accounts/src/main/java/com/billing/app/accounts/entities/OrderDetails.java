package com.billing.app.accounts.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String orderCode;
    private Double price;
    private Double discount;
    private Double gst;
    private Double packageCharge;
    private Double totalPrice;
    private LocalDateTime createdDate;
    private String orderStatus="F";
}
