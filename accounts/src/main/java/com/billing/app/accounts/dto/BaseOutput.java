package com.billing.app.accounts.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BaseOutput {
    String returnMsg;
    String returnCode;
}
