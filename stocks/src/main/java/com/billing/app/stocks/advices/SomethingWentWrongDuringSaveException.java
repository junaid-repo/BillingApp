package com.billing.app.stocks.advices;

public class SomethingWentWrongDuringSaveException extends Exception {

    String errMsg;
    String errCode;

    public SomethingWentWrongDuringSaveException(String code, String msg) {
        super(msg);
        errMsg=msg;
        errCode=code;
    }
}
