package com.billing.app.extracharges.controller;

import com.billing.app.extracharges.dto.BaseOutput;
import com.billing.app.extracharges.entities.ChargesDetails;
import com.billing.app.extracharges.service.ChargesService;
import jakarta.ws.rs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/bs/charges")
public class ChargesController {

    @Autowired
    ChargesService serv;


    @PostMapping("/add")
    ResponseEntity<BaseOutput> addChargesDetails(@RequestBody ChargesDetails req) {
        BaseOutput response = new BaseOutput();
        response=serv.saveChargesDetails(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }
    @GetMapping("/get/{category}")
    ResponseEntity<List<ChargesDetails>> getChargesDetailsByCategory(@PathVariable String category){
        List<ChargesDetails> response= new ArrayList<>();
        response=serv.getChargesDetailsByCategory(category);

        return ResponseEntity.status(HttpStatus.FOUND).body(response);


    }
}
