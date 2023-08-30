package com.billing.app.accounts.repositories;

import com.billing.app.accounts.dto.CollectionRequest;
import com.billing.app.accounts.entities.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountsOrderRepository extends JpaRepository<OrderDetails, Integer> {
}
