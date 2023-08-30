package com.billing.app.extracharges.service;

import com.billing.app.extracharges.dto.BaseOutput;
import com.billing.app.extracharges.entities.ChargesDetails;
import com.billing.app.extracharges.repositories.ChangesSaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChargesService {

    @Autowired
    ChangesSaveRepository chargesSaveRepo;

    public BaseOutput saveChargesDetails(ChargesDetails req) {
        BaseOutput response = new BaseOutput();
        ChargesDetails out = new ChargesDetails();

        out = chargesSaveRepo.save(req);
        if (out.getId() < 1) {
            response.setReturnCode("444");
            response.setReturnMsg("Something went wrong");
            return response;

        }
        response.setReturnCode("202");
        response.setReturnMsg("Created");
        return response;


    }

    public List<ChargesDetails> getChargesDetailsByCategory(String category) {

        List<ChargesDetails> response = new ArrayList<>();

        response=chargesSaveRepo.findByCategory(category);

        return response;


    }
}
