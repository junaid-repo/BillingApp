package com.billing.app.accounts.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.billing.app.accounts.entities.OrderWiseProductDetails;

public interface OrderWiseProductsRepository extends JpaRepository<OrderWiseProductDetails, Integer>{

	@Query(value="select * from order_wise_product_details opd where opd.order_id=?1", nativeQuery = true)
	List<OrderWiseProductDetails> findbyOrderId(Integer id);

}
