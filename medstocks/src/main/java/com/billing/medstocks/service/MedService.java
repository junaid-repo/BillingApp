package com.billing.medstocks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.billing.medstocks.dto.BaseOutput;
import com.billing.medstocks.entities.MedStockDetails;
import com.billing.medstocks.repository.MedEntitySaverRepository;

@Service
public class MedService {

	@Autowired
	MedEntitySaverRepository medSaveRepo;

	public BaseOutput saveStocks(MedStockDetails req) {
		BaseOutput response = new BaseOutput();
		medSaveRepo.save(req);
		response.setReturnCode(201l);
		response.setReturnMsg("Created");

		return response;
	}
	

    public BaseOutput updateStocksCount(String id, String count) {
        BaseOutput response = new BaseOutput();

        Long updatedUnit = medSaveRepo.findUnitCounts(Integer.parseInt(id)) -Integer.parseInt(count);

        MedStockDetails sd =medSaveRepo.findById(Integer.parseInt(id)).get();

        sd.setUnits(updatedUnit);
         sd =medSaveRepo.save(sd);

         if(sd.getId()<0){
             response.setReturnCode(444l);
             response.setReturnMsg("Something went wrong");
             return response;
         }
         response.setReturnMsg("OK");
         response.setReturnCode(201l);

        return response;
    }
    
    public MedStockDetails findProdById(String id) {

        return medSaveRepo.findById(Integer.parseInt(id)).get();
    }

}
