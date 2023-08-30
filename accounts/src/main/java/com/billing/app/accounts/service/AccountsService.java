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

        Double discount;
        Double gst;
        Double mrp=0d;

        Double packageCharge;
        Double totalPrice = 0d;
        Double totalGst = 0d;
        Double totalPkgCharges = 0d;
        Double totalDiscount = 0d;

        List<ProductDetails> products = req.getProducts();


        for (ProductDetails pd : products) {
            Double price;
            Integer pdId = pd.getProductId();

            StockDetails stockDetails = ssc.getStockDetialsById(pdId);
            List<ChargesDetails> chargesDetails = new ArrayList<>();

            log.info("The stock details from external service call is --> " + stockDetails);
            String category = stockDetails.getCategory();
            chargesDetails = ssc.getChargesDetails(category);


            Double tGst = 0d;
            Double tDiscount = 0d;
            Double tPkg = 0d;


            price = stockDetails.getCostPerUnit() * pd.getUnits();

            for (ChargesDetails tempCharges : chargesDetails) {
                if (tempCharges.getType().equals("GST")) {
                    tGst = price * tempCharges.getCharge() / 100;
                }

                if (tempCharges.getType().equals("DIS")) {
                    if (tempCharges.getChargeType().equals("F"))
                        tDiscount = tempCharges.getCharge();
                    else
                        tDiscount = price * tempCharges.getCharge() / 100;

                }
                if (tempCharges.getType().equals("PKG")) {
                    if (tempCharges.getChargeType().equals("F"))
                        tPkg = tempCharges.getCharge();
                    else
                        tPkg = price * tempCharges.getCharge() / 100;

                }

            }

            mrp=mrp+price;
            price = price + tGst + tPkg - tDiscount;
            totalGst = totalGst + tGst;
            totalDiscount = totalDiscount + tDiscount;
            totalPkgCharges = totalPkgCharges + tPkg;
            totalPrice = totalPrice + price;


        }
        response.setPrice(mrp);
        response.setDiscount(totalDiscount);
        response.setGst(totalGst);
        response.setPackageCharge(totalPkgCharges);
        response.setTotalPrice(totalPrice);

        return response;
    }
}