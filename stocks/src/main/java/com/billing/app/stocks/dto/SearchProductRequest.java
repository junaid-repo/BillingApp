package com.billing.app.stocks.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SearchProductRequest {

    private String brand;
    private String category;
    private String  model;
    private Double cost;
    private String searchParam;



}
