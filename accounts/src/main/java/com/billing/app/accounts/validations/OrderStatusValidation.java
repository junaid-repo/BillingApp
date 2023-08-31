package com.billing.app.accounts.validations;

import com.billing.app.accounts.entities.OrderDetails;
import com.billing.app.accounts.repositories.AccountsOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class OrderStatusValidation {

    @Autowired
    AccountsOrderRepository accOrderRepo;

    public OrderDetails validatedOrderStatus(Integer orderId) {
        OrderDetails out = new OrderDetails();
        out = accOrderRepo.checkOrderStatus(orderId);
        return out;
    }
}
