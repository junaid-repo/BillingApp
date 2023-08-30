package com.billing.app.stocks.controller;

import com.billing.app.stocks.advices.SomethingWentWrongDuringSaveException;
import com.billing.app.stocks.dto.BaseOutput;
import com.billing.app.stocks.dto.SearchProductRequest;
import com.billing.app.stocks.entities.ProductCategory;
import com.billing.app.stocks.entities.StockDetails;
import com.billing.app.stocks.services.StockServices;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/bs/stocks")
public class StocksController {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    StockServices serv;


    @PostMapping("/addCategory")
    ResponseEntity<BaseOutput> addCategory(@RequestBody ProductCategory req) throws SomethingWentWrongDuringSaveException {
        BaseOutput response = new BaseOutput();
        response = serv.saveCategory(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/updateStocks")
    ResponseEntity<BaseOutput> addStocks(@RequestBody StockDetails req) throws SomethingWentWrongDuringSaveException {

        BaseOutput response = new BaseOutput();
        response = serv.saveStocks(req);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PostMapping("/updateStocksCounts/{id}/{count}")
    ResponseEntity<BaseOutput> updateStockCounts(@PathVariable String id, @PathVariable String count)  {

        BaseOutput response = new BaseOutput();
        response = serv.updateStocksCount(id, count);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/findProductById/{id}")
    ResponseEntity<StockDetails> findProductById(@PathVariable String id) {
        StockDetails response = new StockDetails();

        response = serv.findProdById(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }

    @GetMapping("/searchProduct")
    ResponseEntity<List<StockDetails>> searchProduct(@RequestBody SearchProductRequest req) {
        List<StockDetails> response = new ArrayList<>();

        response = serv.searchProduct(req);

        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }


}
