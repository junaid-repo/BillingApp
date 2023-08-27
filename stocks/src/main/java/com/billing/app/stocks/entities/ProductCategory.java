package com.billing.app.stocks.entities;

import jakarta.persistence.*;
import lombok.*;


@Table(name = "ProductCategory")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProductCategory {

    @Id
    private String categoryCode;
    private String categoryName;


}
