package com.billing.app.extracharges.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BaseOutput {
    private String returnMsg;
    private String returnCode;
}
