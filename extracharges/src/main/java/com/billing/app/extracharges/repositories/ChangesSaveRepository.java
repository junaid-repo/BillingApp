package com.billing.app.extracharges.repositories;

import com.billing.app.extracharges.entities.ChargesDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChangesSaveRepository extends JpaRepository<ChargesDetails, Integer> {

    @Query(value="select * from charges_details cd where cd.category=?1", nativeQuery = true)
    List<ChargesDetails> findByCategory(String category);
}
