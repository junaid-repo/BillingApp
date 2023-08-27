package com.billing.app.accounts.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDetails {
    private Integer productId;
    private Integer units;
    private Integer discountPercentage;
    private Integer extraCharges;
}
