package com.billing.app.accounts.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.billing.app.accounts.apiClassModels.CustomerDetails;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderDetailsResponse {
    private String orderCode;
    private Double price;
    private Double discount;
    private Double gst;
    private Double packageCharge;
    private Double totalPrice;
    private LocalDateTime createdDate;
    private String orderStatus="F";
    private String customerCode;
    private Integer units;
    List<OrderWiseProductDetailsDTO> productDetails= new ArrayList<>();
    CustomerDetails customerDetail;

}
