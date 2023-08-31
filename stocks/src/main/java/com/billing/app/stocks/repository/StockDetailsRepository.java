package com.billing.app.stocks.repository;

import com.billing.app.stocks.entities.StockDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StockDetailsRepository extends JpaRepository<StockDetails, String> {

    @Query(value = "SELECT sd.units FROM STOCKS_DETAILS sd where sd.id=?1", nativeQuery = true)
    Long findUnitCounts(Integer id);

    @Query( value = "select * from stocks_details sd where sd.brand =?1 or sd.category =?2 or model =?3", nativeQuery = true)
    List<StockDetails> searchStocksWithOr( String brand, String category, /*@Param("cost") Double cost,*/  String model);

    @Query( value = "select * from stocks_details sd where sd.brand = COALESCE(?1, sd.brand) and sd.category = COALESCE(?2,sd.category) and sd.model =COALESCE(?3, 'sd.model')", nativeQuery = true)
    List<StockDetails> searchStocksWithAnd( String brand, String category, /*@Param("cost") Double cost,*/  String model);
}
