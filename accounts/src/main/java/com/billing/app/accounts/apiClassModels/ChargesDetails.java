package com.billing.app.accounts.apiClassModels;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@ToString
public class ChargesDetails {


    private Integer id;

    private String category;
    private String type;
    private Double charge;
    private String chargeType;
}
