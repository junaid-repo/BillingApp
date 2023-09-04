package com.billing.customers.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseOutput {

    String customerCode;
    String returnMsg;
    Long returnCode;
}
