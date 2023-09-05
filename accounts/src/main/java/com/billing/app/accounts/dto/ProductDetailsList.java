package com.billing.app.accounts.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ProductDetailsList extends BaseInput{
    List<ProductDetails> products;
    private String customerCode;

}
