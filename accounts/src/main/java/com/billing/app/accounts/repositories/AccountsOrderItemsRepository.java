package com.billing.app.accounts.repositories;

import com.billing.app.accounts.entities.ItemCountsEntity;
import com.billing.app.accounts.entities.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountsOrderItemsRepository extends JpaRepository<ItemCountsEntity, Integer> {
}
