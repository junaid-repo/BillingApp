package com.billing.app.accounts.controller;

import com.billing.app.accounts.dto.CollectionRequest;
import com.billing.app.accounts.dto.OrderDetailsResponse;
import com.billing.app.accounts.dto.ProductDetailsList;
import com.billing.app.accounts.dto.TotalCartValue;
import com.billing.app.accounts.entities.CollectionResponse;
import com.billing.app.accounts.entities.OrderDetails;
import com.billing.app.accounts.service.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    @GetMapping("/getOrderDetails/{orderCode}")
    ResponseEntity<OrderDetails> getOrderDetails(@PathVariable String orderCode){
    	OrderDetails response = new OrderDetails();
    	
    	response=serv.getOrderDetails(orderCode);
    	
    	return ResponseEntity.status(HttpStatus.FOUND).body(response);
    	
    }
    @GetMapping("/orderSummry/{orderCode}")
    ResponseEntity<OrderDetailsResponse> gerOrderSummry(@PathVariable String orderCode){
    	OrderDetailsResponse response = new OrderDetailsResponse();
    	
    	response=serv.getOrderSummry(orderCode);
    	
    	return ResponseEntity.status(HttpStatus.FOUND).body(response);
    	
    }
    @PostMapping("/doCollection")
    ResponseEntity<CollectionResponse> doCollection(@RequestBody CollectionRequest req){
        CollectionResponse response= new CollectionResponse();
        response=serv.doCollection(req);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

}
