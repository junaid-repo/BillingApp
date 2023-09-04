package com.billing.medstocks.entities;

import java.time.LocalDate;

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

//@Table(name = "MedStocksDetails")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MedStockDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id = 0;

	private String category = null;
	private String name = null;
	private String brand = null;
	private String batchNo = null;
	private Long units = 0l;
	private LocalDate expiryDate;

}
