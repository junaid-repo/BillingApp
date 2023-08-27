package com.billing.app.stocks.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseOutput {

    Integer id;
    String returnMsg;
    Long returnCode;
}
