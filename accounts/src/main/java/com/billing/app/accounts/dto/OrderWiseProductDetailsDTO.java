package com.billing.app.accounts.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderWiseProductDetailsDTO {
	private String productName;
	private Double mrp;
	private Integer units;
	private Double cost;
	private Double discount;
	private Double gst;
	private Double pkgChg;
	private Double totalAmt;
	

}
