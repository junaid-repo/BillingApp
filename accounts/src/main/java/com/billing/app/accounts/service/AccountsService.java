package com.billing.app.accounts.service;

import com.billing.app.accounts.apiClassModels.ChargesDetails;
import com.billing.app.accounts.apiClassModels.StockDetails;
import com.billing.app.accounts.dto.ProductDetails;
import com.billing.app.accounts.dto.ProductDetailsList;
import com.billing.app.accounts.dto.TotalCartValue;
import com.billing.app.accounts.externalapi.AccountsCommunicationFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class AccountsService {

    @Autowired
    AccountsCommunicationFacade ssc;

    Logger log = LoggerFactory.getLogger(this.getClass());

    public TotalCartValue billing(ProductDetailsList req) {
        TotalCartValue response = new TotalCartValue();


        Double packageCharge;
        Double totalPrice = 0d;
        Double totalGst = 0d;
        Double totalDiscount = 0d;

        List<ProductDetails> products = req.getProducts();


        for (ProductDetails pd : products) {
            Integer pdId = pd.getProductId();

            StockDetails stockDetails = ssc.getStockDetialsById(pdId);
            List<ChargesDetails> chargesDetails = new ArrayList<>();

            log.info("The stock details from external service call is --> "+ stockDetails);
            chargesDetails = ssc.getChargesDetails(stockDetails.getCategory());

            Double price;
            Double discount;
            Double gst;





            price = stockDetails.getCostPerUnit()*pd.getUnits();

            for(ChargesDetails tempCharges: chargesDetails){
                if(tempCharges.getType().equals("GST")){
                    gst=price*tempCharges.getCharge()/100;
                }

            }



            gst = price * 0.18;
            packageCharge = 2.0;
            discount = price * 0.10;
            price = price + gst + packageCharge - discount;
            totalGst = totalGst + gst;
            totalDiscount = totalDiscount + discount;
            totalPrice = totalPrice + price;


        }


        return response;
    }
}
