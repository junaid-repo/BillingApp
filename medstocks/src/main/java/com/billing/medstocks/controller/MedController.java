package com.billing.medstocks.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.billing.medstocks.dto.BaseOutput;
import com.billing.medstocks.entities.MedStockDetails;
import com.billing.medstocks.service.MedService;

@RestController
@RequestMapping("/bs/medStocks")
public class MedController {

	@Autowired
	MedService serv;

	@PostMapping("/updateStocks")
	ResponseEntity<BaseOutput> addStocks(@RequestBody MedStockDetails req) {

		BaseOutput response = new BaseOutput();
		response = serv.saveStocks(req);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PostMapping("/updateStocksCounts/{id}/{count}")
	ResponseEntity<BaseOutput> updateStockCounts(@PathVariable String id, @PathVariable String count) {

		BaseOutput response = new BaseOutput();
		response = serv.updateStocksCount(id, count);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping("/findProductById/{id}")
	ResponseEntity<MedStockDetails> findProductById(@PathVariable String id) {
		MedStockDetails response = new MedStockDetails();

		response = serv.findProdById(id);
		return ResponseEntity.status(HttpStatus.FOUND).body(response);
	}


}
