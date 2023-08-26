package com.billing.app.stocks.controller;

import com.billing.app.stocks.dto.BaseOutput;
import com.billing.app.stocks.entities.StockDetails;
import com.billing.app.stocks.services.StockServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bs/")
public class StocksController {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    StockServices serv;

    @PostMapping("/addStocks")
    ResponseEntity<BaseOutput> addStocks(@RequestBody StockDetails req){

        BaseOutput response = new BaseOutput();
        response=serv.saveStocks(req);

     return    ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
