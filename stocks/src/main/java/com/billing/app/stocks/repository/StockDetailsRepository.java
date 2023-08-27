package com.billing.app.stocks.repository;

import com.billing.app.stocks.entities.StockDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StockDetailsRepository extends JpaRepository<StockDetails, String> {

    @Query(value = "SELECT sd.units FROM STOCKS_DETAILS sd where sd.id=?1", nativeQuery = true)
    Long findUnitCounts(Integer id);

    @Query(value = "select * from stocks_details sd where sd.brand like '%:?1%' and sd.category like '%:?2%' or sd.cost_per_unit between 0 and ?3 and model like '%:?4%'", nativeQuery = true)
    List<StockDetails> searchStocksWithOr(String brand, String category, Double cost, String model);
}
