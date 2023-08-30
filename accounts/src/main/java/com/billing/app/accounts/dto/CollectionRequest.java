package com.billing.app.accounts.dto;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name="CollectionDetails")
@AllArgsConstructor
@NoArgsConstructor
public class CollectionRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String custCode;
    private String empId;
    private String voucherNo;
    private LocalDateTime date;
    private Double amount;
    private String collectionMode;
    private String status;
    private Integer orderId;

}
