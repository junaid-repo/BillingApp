package com.billing.app.accounts.repositories;

import com.billing.app.accounts.dto.CollectionRequest;
import com.billing.app.accounts.entities.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountsOrderRepository extends JpaRepository<OrderDetails, Integer> {

    @Query(value = "select * from order_details od where od.id=?1", nativeQuery = true)
    OrderDetails checkOrderStatus(Integer orderId);

    @Query(value="update order_details set order_status=?2 where id=?1", nativeQuery = true)
    OrderDetails updateOrderStatus(Integer orderid, String status);
}
