package com.billing.app.stocks.entities;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "StocksDetails")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StockDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;


}
