package com.billing.app.stocks.advices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SomethingWentWrongDuringSaveException.class)
    ResponseEntity<Map<String, Object>> somethingWrongExp(SomethingWentWrongDuringSaveException ex) {

        Map<String, Object> errMap = new HashMap<>();

        errMap.put("errMsg", ex.errMsg);
        errMap.put("errCode", ex.errCode);

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errMap);

    }
}
