package com.billing.app.accounts.repositories;

import com.billing.app.accounts.entities.ItemCountsEntity;
import com.billing.app.accounts.entities.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountsOrderItemsRepository extends JpaRepository<ItemCountsEntity, Integer> {

    @Query(value="select * from item_counts_entity ice where ice.order_id=?1", nativeQuery = true)
    List<ItemCountsEntity> findByOrderId(Integer odId);

    @Query(value="select * from order_details od where od.order_code=?1", nativeQuery = true)
	OrderDetails findByOrderCode(String orderCode);
}
