package com.billing.app.accounts.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderWiseProductDetails {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    private Integer orderId;
    private Integer productId;
    private Double mrp;
    private Integer untis;
    private Double cost;
    private Double discount;
    private Double gst;
    private Double pkgChg;
    private Double totalAmt;

}
