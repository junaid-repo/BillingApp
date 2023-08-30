package com.billing.app.accounts.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ItemCountsEntity {
    private Integer orderId;
    private Integer itemId;
    private Integer counts;
    private LocalDateTime createdDate;
    private String status;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
}
