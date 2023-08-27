package com.billing.app.accounts.service;

import com.billing.app.accounts.dto.ProductDetails;
import com.billing.app.accounts.dto.ProductDetailsList;
import com.billing.app.accounts.dto.TotalCartValue;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountsService {
    public TotalCartValue billing(ProductDetailsList req) {
        TotalCartValue response = new TotalCartValue();

        return response;
    }
}
