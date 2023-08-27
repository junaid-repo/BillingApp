package com.billing.app.accounts.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TotalCartValue {

    private Double price;
    private Double discount;
    private Double gst;
    private Double packageCharge;
    private Double totalPrice;
}
