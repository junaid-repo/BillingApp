package com.billing.app.accounts.controller;

import com.billing.app.accounts.dto.ProductDetailsList;
import com.billing.app.accounts.dto.TotalCartValue;
import com.billing.app.accounts.service.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bs/accounts")
public class AccountsController {

    @Autowired
    AccountsService serv;



    @PostMapping("/calulcateCartValue")
    ResponseEntity<TotalCartValue> sumUpCart(@RequestBody ProductDetailsList req){

        TotalCartValue response= new TotalCartValue();

        response=serv.billing(req);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

}
