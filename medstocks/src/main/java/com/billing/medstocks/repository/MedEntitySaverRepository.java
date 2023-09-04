package com.billing.medstocks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.billing.medstocks.entities.MedStockDetails;

public interface MedEntitySaverRepository extends JpaRepository<MedStockDetails, Integer> {

	@Query(value = "SELECT sd.units FROM MED_STOCKS_DETAILS sd where sd.id=?1", nativeQuery = true)
	Long findUnitCounts(Integer id);

	@Query(value = "select * from MED_STOCKS_DETAILS sd where sd.brand =?1 or sd.category =?2 or model =?3", nativeQuery = true)
	List<MedStockDetails> searchStocksWithOr(String brand, String category,
			/* @Param("cost") Double cost, */ String model);

	@Query(value = "select * from MED_STOCKS_DETAILS sd where sd.brand = COALESCE(?1, sd.brand) and sd.category = COALESCE(?2,sd.category) and sd.batchNo =COALESCE(?3, 'sd.batchNo')", nativeQuery = true)
	List<MedStockDetails> searchStocksWithAnd(String brand, String category,
			/* @Param("cost") Double cost, */ String model);

}
