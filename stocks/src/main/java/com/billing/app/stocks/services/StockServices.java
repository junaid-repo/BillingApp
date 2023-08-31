package com.billing.app.stocks.services;

import com.billing.app.stocks.advices.SomethingWentWrongDuringSaveException;
import com.billing.app.stocks.dto.BaseOutput;
import com.billing.app.stocks.dto.SearchProductRequest;
import com.billing.app.stocks.entities.ProductCategory;
import com.billing.app.stocks.entities.StockDetails;
import com.billing.app.stocks.repository.CategorySaveRepository;
import com.billing.app.stocks.repository.StockDetailsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StockServices {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CategorySaveRepository catSaveRepo;

    @Autowired
    StockDetailsRepository stockSaveRepo;

    public BaseOutput saveCategory(ProductCategory req) throws SomethingWentWrongDuringSaveException {
        BaseOutput response = new BaseOutput();
        ProductCategory out = new ProductCategory();
        out = catSaveRepo.save(req);


        response.setReturnMsg("Success");
        response.setReturnCode(201l);


        return response;

    }


    public BaseOutput saveStocks(StockDetails req) throws SomethingWentWrongDuringSaveException {
        StockDetails out = new StockDetails();
        BaseOutput response = new BaseOutput();

        if (req.getId() > 0) {
            Long updatedUnit = stockSaveRepo.findUnitCounts(req.getId()) + req.getUnits();
            log.info("The updated unit is " + String.valueOf(updatedUnit));
            req.setUnits(updatedUnit);
        }

        out = stockSaveRepo.save(req);


        if (out.getId() < 1) {
            response.setReturnCode(400l);
            response.setReturnMsg("Something went wrong");

            throw new SomethingWentWrongDuringSaveException("402", "Something went wrong during the save");

        } else {
            response.setId(out.getId());
            response.setReturnMsg("Success");
            response.setReturnCode(201l);
        }

        return response;

    }



    public StockDetails findProdById(String id) {

        return stockSaveRepo.findById(id).get();
    }


    public List<StockDetails> searchProduct(SearchProductRequest req) {

        List<StockDetails> response = new ArrayList<>();

        //response = stockSaveRepo.searchStocksWithOr(req.getBrand(), req.getCategory(), /*req.getCost(),*/ req.getModel());

        response = stockSaveRepo.searchStocksWithAnd(req.getBrand(), req.getCategory(), /*req.getCost(),*/ req.getModel());


        return response;
    }

    public BaseOutput updateStocksCount(String id, String count) {
        BaseOutput response = new BaseOutput();

        Long updatedUnit = stockSaveRepo.findUnitCounts(Integer.parseInt(id)) -Integer.parseInt(count);

        StockDetails sd =stockSaveRepo.findById(id).get();

        sd.setUnits(updatedUnit);
         sd =stockSaveRepo.save(sd);

         if(sd.getId()<0){
             response.setReturnCode(444l);
             response.setReturnMsg("Something went wrong");
             return response;
         }
         response.setReturnMsg("OK");
         response.setReturnCode(201l);

        return response;
    }
}
