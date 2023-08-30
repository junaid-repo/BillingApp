package com.billing.app.accounts.entities;

import com.billing.app.accounts.dto.BaseOutput;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class CollectionResponse extends BaseOutput {


    private String voucherNo;
    private LocalDateTime date;
}
